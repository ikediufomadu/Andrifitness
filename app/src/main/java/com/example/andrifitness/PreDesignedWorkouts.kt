import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavHostController

@Composable
fun PreDesignedWorkoutLayout(navController: NavHostController) {
    val constraints = ConstraintSet {
        val topButtons = createRefFor("topButtons")
        val pageName = createRefFor("pageName")
        val workouts = createRefFor("workouts")
        val bottomButtons = createRefFor("bottomButtons")

        constrain(topButtons) {
            top.linkTo(parent.top)
        }
        constrain(pageName) {
            top.linkTo(topButtons.bottom)
        }
        constrain(workouts) {
            top.linkTo(pageName.bottom)
        }
        constrain(bottomButtons) {
            top.linkTo(workouts.bottom)
        }
    }
    ConstraintLayout(
        constraints, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp)
    ) {
        // Show a column full of premade workouts
        Column() {

        }
    }
}