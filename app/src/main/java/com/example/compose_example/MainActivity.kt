package com.example.compose_example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.model.Blog
import com.example.compose_example.model.DummyBlogProvider
import com.example.compose_example.ui.theme.Compose_ExampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //            Compose_ExampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("PDP")
//                }
//            }

//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Greeting(name = "PDP Academy")
//            }


//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                SetTextStyle(
//                    "PDP Academy in Tashkent",
//                    TextStyle(
//                        fontSize = 32.sp,
//                        fontWeight = FontWeight.Bold
//                    ),
//                    1
//                )
//
//                SimpleTextFieldComponent()
//                SimpleTextFieldComponent2()
//            }

//            SimpleColumnComponent()

//            lazyColumnDemo()

//            SimpleBoxComponent()

//            SimpleButtonComponent()

//            SimpleTextComponent()

//            AlertDialogComponent()

//            TopAppBarComponent()

//            BottomNavigationWithLabelComponent()

//            SimpleCheckboxComponent()

//            SimpleCircularProgressBarComponent()
//            SimpleLinearProgressBarComponent()

//            SimpleSliderComponent()

//            SnackbarScreen()

//            CustomViewComponent()

            CrossFadeAnimation()
        }
    }
}
@Composable
fun CrossFadeAnimation() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Gray)
    var current by remember { mutableStateOf(colors[0]) }
    Column(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = current) { color ->
            Box(Modifier.fillMaxSize().clickable(
                onClick = {
                    current = colors.random()
                }
            ).background(color))
            Text(
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "Click To See"
            )
        }
    }
}

//Custom View
@Composable
fun CustomViewComponent() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        drawRect(
            color = Color.Red,
            // topLeft is the coordinate of top-left point
            topLeft = Offset(0f, 0f),
            size = Size(800f, 400f)
        )
        drawArc(
            Color.Gray,
            startAngle = 0f,
            sweepAngle = 120f,
            useCenter = true,
            size = Size(600f, 600f),
            topLeft = Offset(300f, 300f)
        )
    }
}

//Snackbar
@Composable
fun SnackbarScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
        FloatingActionButton(
            onClick = {
                //Important part here
                scope.launch {
                    snackbarHostState.showSnackbar("Hello there")
                }
                //
            },
            content = { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
        )

    SnackbarHost(hostState = snackbarHostState)
}

//Material Slider -> Seekbar
@Composable
fun SimpleSliderComponent(){
    var sliderValue by remember {
        mutableStateOf(0.4f)
    }
    Slider(
        value = sliderValue,
        modifier = Modifier.padding(8.dp),
        onValueChange = { newValue ->
            sliderValue = newValue
        }
    )
    Text(text = "Slider value : $sliderValue",
    modifier = Modifier.padding(8.dp))
}

//Circular ProgressBar
@Composable
fun SimpleCircularProgressBarComponent() {
    CircularProgressIndicator(
        modifier = Modifier.padding(16.dp)
    )
}

//Circular ProgressBar
@Composable
fun SimpleLinearProgressBarComponent() {
    LinearProgressIndicator(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

//CheckBox
@Composable
fun SimpleCheckboxComponent() {
    val checkedState = remember {
        mutableStateOf(true)
    }
    Row {
        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.padding(16.dp),
            onCheckedChange = { checkedState.value = it },
        )
        Text(text = "Checkbox Example", modifier = Modifier.padding(16.dp))

        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.padding(16.dp),
            onCheckedChange = { checkedState.value = it },
        )
        Text(text = "Checkbox Example", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun BottomNavigationWithLabelComponent() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Blogs", "Profile")
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Blue,
        contentColor = Color.Red
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                label = { Text(text = item) },
                icon = { Icon(Icons.Filled.Favorite, "Icon1") },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                }
            )
        }
    }
}

@Composable
fun TopAppBarComponent() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text("App Name") },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Menu, "Icon1")
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Favorite, "Icon2")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Favorite, "Icon3")
            }
        }
    )
}

@Composable
fun AlertDialogComponent() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Alert Dialog") },
            text = { Text("Hello I am Alert Dialog") },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        /**
                         * do some other action
                         */
                    }) {
                    Text("Confirm")
                }
            }, dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    /**
                     * Do some other action
                     */
                }) {
                    Text("Dismiss")
                }
            }, backgroundColor = Color.Black,
            contentColor = Color.White
        )
    }
}

@Composable
fun SimpleImageComponent() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
fun SimpleTextComponent() {
    val context = LocalContext.current
    Text(
        text = "Click Me",
        textAlign = TextAlign.Center,
        color = Color.Blue,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(onClick = {
                Toast
                    .makeText(context, "Thanks for clicking", Toast.LENGTH_SHORT)
                    .show()
            })
    )

}

@Composable
fun SimpleButtonComponent() {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Thanks for clicking", Toast.LENGTH_SHORT).show()
        },
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text("Click Me")
    }
}

//Box bu bola layoutlarni bir biriga nisbatan ustma ust joylashtiruvchi composable layout hisoblanadi.
@Composable
fun SimpleBoxComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Image(
                painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "I am a text over Image",
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}


//LazyColumn -> RecyclerView kabi faqat mobil ekranda ko'rinadigan elementlarni yuklaydi.
@Composable
fun lazyColumnDemo() {
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
//    LazyRow(modifier = Modifier.fillMaxWidth()) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            when (item) {
                "A" -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Nothing
                }
                "D" -> {
                    Text(text = item)
                }
                else -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
            }
        })
    }
}

//Column->Vertical LinearLayout
@Composable
fun SimpleColumnComponent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello I'm a Text1", color = Color.Black)
        Text(text = "Hello I'm a Text2", color = Color.Blue)
        Text(text = "Hello I'm a Text3", color = Color.Unspecified)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//set style to Text
@Composable
fun SetTextStyle(displayText: String, style: TextStyle? = null, maxLines: Int? = null) {
    Text(
        text = "Hello $displayText!",
        modifier = Modifier.padding(16.dp),
        style = style ?: TextStyle.Default,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines ?: Int.MAX_VALUE
    )
}

//to get data by TextField
@Composable
fun SimpleTextFieldComponent() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var text by remember { mutableStateOf(TextFieldValue("Enter text here")) }
        TextField(
            value = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onValueChange = { text = it },
        )
    }
}

@Composable
fun SimpleTextFieldComponent2() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = { Text("Enter Password") },
            onValueChange = { text = it },
            label = { Text(text = "Label") }
        )
    }
}

@Composable
fun SimpleTextComponent(s: String) {
    Text(text = "Hello $s!")
}


@Preview
@Composable
fun BlogInfo(@PreviewParameter(DummyBlogProvider::class) blog: Blog) {
    SimpleTextComponent("${blog.name} by ${blog.author}")
}

@Preview(showBackground = true, name = "Named Greeting")
@Composable
fun DefaultPreview() {
    Compose_ExampleTheme {
//        Greeting("Android")
    }
}