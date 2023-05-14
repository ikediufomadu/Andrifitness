import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController
import com.example.andrifitness.*

@Composable
fun PreDesignedWorkoutLayout(navController: NavHostController) {
    val constraints = ConstraintSet {
        val pageName = createRefFor("pageName")
        val premadeWorkouts = createRefFor("premadeWorkouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(pageName) {
            top.linkTo(parent.top)
        }
        constrain(premadeWorkouts) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(premadeWorkouts.bottom)
        }
    }
    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
                .layoutId("pageName"),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Premade Workouts",
                fontSize = WTextSize,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
                .layoutId("premadeWorkouts")
        ) {
            PopulatePremadeCard()
            DisplayPremadeCards()
        }
        BottomButtons(navController)
    }
}

data class PremadeCardData(
    val title: String,
    val day: String,
    val notes: String
)

val PremadeCardList = mutableListOf<PremadeCardData>()
@Composable
fun AddCardToList(PremadeCardData: PremadeCardData) {
    PremadeCardList.add(PremadeCardData)
}
@Composable
fun BuildCard(title: String, day: String, notes: String = "") {
    val card = PremadeCardData (title = title, day = day, notes = notes)
    AddCardToList(PremadeCardData = card)
}

@Composable
fun PopulatePremadeCard() {
    val workoutTitle = arrayOf("Squats", "Lunges", "Push-ups", "Plank", "Burpees", "Jumping jacks", "Bicycle crunches", "Mountain climbers", "Leg raises", "Tricep dips", "Wall sits", "Standing calf raises", "Jump rope", "Swimming", "Running")
    val workoutDays = arrayOf("Monday", "Thursday", "Tuesday", "Wednesday", "Monday", "Thursday", "Tuesday", "Wednesday", "Saturday", "Friday", "Wednesday", "Saturday", "Friday", "Wednesday", "Thursday")
    val workoutNotes = arrayOf(
        "Squats: This exercise works the muscles in your legs, including your quadriceps, hamstrings, and glutes. Make sure to keep your knees over your ankles and your back straight.",
        "Lunges: Like squats, lunges work your leg muscles, but also require more balance and coordination. Keep your front knee over your ankle and your back knee hovering just above the ground.",
        "Push-ups: This classic exercise works your chest, triceps, and shoulders, as well as your core muscles. Keep your body straight and your elbows close to your body as you lower yourself down.",
        "Plank: Planks are great for strengthening your core muscles, including your abs, back, and hips. Keep your body straight and avoid letting your hips sag.",
        "Burpees: Burpees are a full-body exercise that combines squats, push-ups, and jumps. They are great for building strength and cardiovascular endurance.",
        "Jumping jacks: This simple exercise is a great warm-up or cardio workout. It works your leg muscles and gets your heart rate up.",
        "Bicycle crunches: This exercise targets your abs and obliques. Make sure to keep your lower back pressed into the ground and your elbows wide.",
        "Mountain climbers: Mountain climbers are a great way to work your abs, hips, and shoulders while also getting your heart rate up. Keep your body straight and your hips low.",
        "Leg raises: This exercise is great for targeting your lower abs. Keep your lower back pressed into the ground and avoid letting your legs drop too low.",
        "Tricep dips: Tricep dips are a great way to target your upper arms. Make sure to keep your elbows close to your body and your shoulders down.",
        "Wall sits: This exercise is great for strengthening your leg muscles, especially your quadriceps. Make sure to keep your back pressed into the wall and your knees over your ankles.",
        "Standing calf raises: This exercise works your calf muscles. Make sure to rise up onto your toes as high as you can and lower your heels back down slowly.",
        "Jump rope: Jumping rope is a great cardio workout that also improves your coordination and footwork. Try different footwork patterns and jumping speeds to mix things up.",
        "Swimming: Swimming is a low-impact, full-body workout that is great for building endurance and muscle strength. Try different strokes to work different muscle groups.",
        "Running: Running is a great way to improve your cardiovascular fitness and build leg muscle strength. Start slow and gradually increase your distance and speed over time."
        )
    for (i in workoutTitle.indices) {
        BuildCard(title = workoutTitle[i], day = workoutDays[i], workoutNotes[i])
    }
}
@Composable
fun DisplayPremadeCards() {
    val cards = remember { mutableStateOf(PremadeCardList) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(cards.value) { card ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                backgroundColor = Color.White,
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Name: " + card.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(text = "Day: " + card.day, fontSize = 16.sp)
                    Text(text = "Notes: " + card.notes, fontSize = 16.sp)
                }
            }
        }
    }
}