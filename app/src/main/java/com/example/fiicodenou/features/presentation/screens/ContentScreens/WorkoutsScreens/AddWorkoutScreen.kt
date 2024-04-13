package com.example.fiicodenou.features.presentation.screens.ContentScreens.WorkoutsScreens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fiicodeapp.features.presentation.components.FitnessAppButton
import com.example.fiicodeapp.features.presentation.components.FitnessAppTextField
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Exercise
import com.example.fiicodenou.features.domain.models.Realm_Objects.Workouts.Workout
import com.example.fiicodenou.features.presentation.components.BottomBarFitnessApp
import com.example.fiicodenou.features.presentation.viewmodels.WorkoutsViewModel
import com.example.fiicodenou.ui.theme.darkerPurple
import com.example.fiicodenou.ui.theme.lighterPurple
import com.example.fiicodenou.ui.theme.lighterRed
import com.example.fiicodenou.ui.theme.myYellow
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.toRealmList
import java.text.DateFormat
import java.util.Calendar

@Composable
fun AddWorkoutScreen(
    navController: NavController
){
    Column {
        MainAddWorkout(navController)
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {
            BottomBarFitnessApp(navController)
        }
    }
}
@Composable
fun MainAddWorkout(
    navController: NavController,
    workoutsViewModel: WorkoutsViewModel = hiltViewModel()
){
    var date by remember{
        mutableStateOf("")
    }

    var type by remember{
        mutableStateOf("")
    }

    var numEx by remember{
        mutableStateOf("")
    }

    var exercise by remember{
        mutableStateOf("")
    }

    var muscleGroup by remember{
        mutableStateOf("")
    }

    var duration by remember{
        mutableStateOf("")
    }

    var sets by remember{
        mutableStateOf("")
    }

    var reps by remember{
        mutableStateOf("")
    }

    val listOfExercise = realmListOf<Exercise>()

    val context = LocalContext.current

    Card(modifier = Modifier
        .fillMaxSize(),
        colors = CardDefaults.cardColors(
            darkerPurple
        ),
        shape = RectangleShape
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "MyFitnesApp",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 40.sp
                    )

                    Text(modifier = Modifier.padding(top = 15.dp),
                        text = "Add Workout",
                        style = MaterialTheme.typography.bodyLarge,
                        color = lighterRed,
                        fontSize = 30.sp
                    )
                }
            }

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = type,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })type=it
                },
                label = "Type (Push,Pull etc)",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = numEx,
                onTextChange = {
                    if(it.all {char->
                            char.isDigit()
                        })numEx=it
                },
                label = "Number of exercises",
                color = darkerPurple,
                textColor = lighterRed
            )

            HorizontalDivider(modifier = Modifier.padding(top = 20.dp))

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = exercise,
                onTextChange = {
                    if(it.all {char->
                            char.isDefined()
                        })exercise=it
                },
                label = "Name of the Exercise",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = muscleGroup,
                onTextChange = {
                    if(it.all {char->
                            char.isLetter()
                        })muscleGroup=it
                },
                label = "Muscle Group",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = duration,
                onTextChange = {
                    if(it.all {char->
                            char.isDigit()
                        })duration=it
                },
                label = "Duration",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = sets,
                onTextChange = {
                    if(it.all {char->
                            char.isDigit()
                        })sets=it
                },
                label = "Working Sets",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppTextField(
                modifier = Modifier.padding(top = 40.dp,bottom = 20.dp),
                text = reps,
                onTextChange = {
                    if(it.all {char->
                            char.isDigit()
                        })reps=it
                },
                label = "All reps",
                color = darkerPurple,
                textColor = lighterRed
            )

            FitnessAppButton(text = "Save Exercise",
                onButClick = {
                    if(exercise.isNotEmpty() || muscleGroup.isNotEmpty()
                        || duration.isNotEmpty() || sets.isNotEmpty()
                        || reps.isNotEmpty()){

                        val exerciseSave = Exercise().apply {
                            this.name = exercise
                            this.targetedMuscleGroup = muscleGroup
                            this.duration = duration.toIntOrNull()!!
                            this.sets = sets.toIntOrNull()!!
                            this.reps = reps.toIntOrNull()!!
                        }
                        exercise=""
                        muscleGroup=""
                        duration=""
                        sets=""
                        reps=""
                        listOfExercise.add(exerciseSave)

                    }else{
                        Toast.makeText(context,"Add all values",Toast.LENGTH_SHORT).show()
                    }

                },
                color = lighterPurple,
                textColor = lighterRed
            )

            LazyRow(modifier = Modifier.padding(top = 30.dp)) {
                items(listOfExercise.size){
                    ExericseCard(exercise = listOfExercise[it])
                    Log.d("exerciseAdd",listOfExercise[it].name)
                }
            }

            FitnessAppButton(text = "Save Workout",
                onButClick = {
                    //TO ADD EMPTY TYPE,DATE,ETC CASES

                    val calendar = Calendar.getInstance().time
                    val timer = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar)

                    val workout = Workout().apply {
                        this.type = type
                        this.date = timer
                        this.numberOfExercises = numEx.toIntOrNull()!!
                        this.exercises = listOfExercise
                    }
                    workoutsViewModel.addWorkout(workout)
                    navController.navigate("MainWorkoutScreen")
                },
                color = lighterPurple,
                textColor = lighterRed
            )
        }
    }
}

@Composable
fun ExericseCard(
    exercise: Exercise
){
    Card(modifier = Modifier
        .height(240.dp)
        .width(260.dp)
        .padding(start = 20.dp, end = 20.dp),
        colors = CardDefaults.cardColors(
            myYellow
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Text(text = "Name: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = exercise.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "Muscle Worked: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = exercise.targetedMuscleGroup,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "Duration (seconds):  ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = exercise.duration.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "Sets:  ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = exercise.sets.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ) {
                Text(text = "All reps:  ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = exercise.reps.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}