package com.example.movieproject.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieproject.models.Movie
import kotlin.random.Random

@Composable
fun MovieRow(movies: Movie, onItemClick: (String) -> Unit) {

    var icon by remember {
        mutableStateOf(Icons.Default.KeyboardArrowUp)
    }
    var visible by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .clickable { onItemClick(movies.id) }
            .padding(4.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp), verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)), Alignment.Center
            ) {
                var randomPic by remember {
                    mutableStateOf(Random.nextInt(movies.images.size - 1))
                }

                AsyncImage(model = movies.images[randomPic], contentDescription = movies.title)
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(50.dp)
                        .padding(4.dp),
                    tint = Color.Cyan
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (!visible) {
                            visible = true
                            icon = Icons.Default.KeyboardArrowDown
                        } else {
                            visible = true
                            icon = Icons.Default.KeyboardArrowUp
                        }
                    }
                    .size(60.dp)
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = movies.title, style = MaterialTheme.typography.h6)
                Icon(imageVector = icon, contentDescription = "", modifier = Modifier
                    .animateContentSize()
                    .clickable {
                        if (!visible) {
                            visible = true
                            icon = Icons.Default.KeyboardArrowDown
                        } else {
                            visible = false
                            icon = Icons.Default.KeyboardArrowUp
                        }
                    })
            }
            AnimatedVisibility(visible = visible) {

                Row(modifier = Modifier) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Row() {
                            Text(text = "Genre: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.genre, fontSize = 16.sp)
                        }
                        Row() {
                            Text(
                                text = "Released: ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = movies.year, fontSize = 16.sp)
                        }
                        Row() {
                            Text(
                                text = "Director: ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = movies.director, fontSize = 16.sp)
                        }
                        Row() {
                            Text(text = "Actors: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.actors, fontSize = 16.sp)
                        }

                        Row() {
                            Text(text = "Rating: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.rating, fontSize = 16.sp)
                        }
                        Divider(
                            color = Color.Gray, thickness = 1.dp, modifier = Modifier
                                .padding(10.dp)
                        )
                        Row() {
                            Text(text = "Plot: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = movies.plot, fontSize = 16.sp)
                        }
                    }
                }
                if (!visible) {
                    MovieAttributes(movie = movies)
                }
            }
        }
    }
}

@Composable
fun MovieAttributes(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(5.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Year: ${movie.year}", fontSize = 20.sp)
        Text(text = "Genre: ${movie.genre}", fontSize = 20.sp)
        Text(text = "Director: ${movie.director}", fontSize = 20.sp)
        Text(text = "Actors: ${movie.actors}", fontSize = 20.sp)
        Text(text = "Rating: ${movie.rating}", fontSize = 20.sp)
        Text(text = "Plot: ${movie.plot}", fontSize = 20.sp)
    }
}