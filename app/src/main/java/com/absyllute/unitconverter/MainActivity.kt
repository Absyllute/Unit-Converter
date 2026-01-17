package com.absyllute.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.absyllute.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface (
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {
                UnitConverterTheme {
                    UnitConverter()
                }
            }

        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }

    var inputIsExpanded by remember { mutableStateOf(false) }
    var outputIsExpanded by remember { mutableStateOf(false) }

    val conversionFactor = remember { mutableStateOf(1.0) }
    val outputConversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100 / outputConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Unit Converter", Modifier.padding(15.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {inputValue = it; convertUnits()},
            Modifier.padding(15.dp),
            label = { Text("Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row {

            //Input Box
            Box {
                Button(onClick = {inputIsExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = inputIsExpanded, onDismissRequest = {inputIsExpanded = false}) {

                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            inputUnit =  "Centimeters"
                            conversionFactor.value = 0.01
                            inputIsExpanded = false
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            inputIsExpanded = false
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            inputUnit = "Feet"
                            inputIsExpanded = false
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            inputIsExpanded = false
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(15.dp))

            //output Button
            Box{
                Button(onClick = {outputIsExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }

                DropdownMenu(expanded = outputIsExpanded, onDismissRequest = {outputIsExpanded = false}) {

                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            outputUnit = "Centimeters"
                            outputIsExpanded = false
                            outputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            outputUnit = "Meters"
                            outputIsExpanded = false
                            outputConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            outputUnit = "Feet"
                            outputIsExpanded = false
                            outputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            outputUnit = "Millimeters"
                            outputIsExpanded = false
                            outputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Result: $outputValue $outputUnit")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}