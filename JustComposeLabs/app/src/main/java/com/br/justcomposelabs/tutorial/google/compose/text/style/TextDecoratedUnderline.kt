package com.br.justcomposelabs.tutorial.google.compose.text.style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextDecoratedUnderline() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Text with Underline Decoration",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                fontSynthesis = FontSynthesis.All,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextDecoratedUnderlineSpecificWords() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text =
            buildAnnotatedString {
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                    ),
                ) {
                    append("Text with ")
                }
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                        textDecoration = TextDecoration.Underline,
                        fontSynthesis = FontSynthesis.All,
                    ),
                ) {
                    append("Underline Decoration")
                }
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                    ),
                ) {
                    append(
                        "on specific parts. Testing min and max lines parameters to see how it behaves when the text is too long and needs to be truncated or wrapped.",
                    )
                }
            },
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            minLines = 1,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextDecoratedUnderlineWithOverflow() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Text with Underline Decoration and Overflow Handling. This text is intentionally long to demonstrate how the Text composable handles overflow when the text exceeds the available space. The text should be truncated with an ellipsis at the end.",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                fontSynthesis = FontSynthesis.All,
            ),
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextCombiningDecorationUnderAndLineThrough() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Text with Underline and LineThrough Decoration",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                textDecoration =
                TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough,
                    ),
                ),
                fontSynthesis = FontSynthesis.All,
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextCombiningDecorationUnderAndLineThroughSpecificWords() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text =
            buildAnnotatedString {
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                    ),
                ) {
                    append("Text with ")
                }
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                        textDecoration =
                        TextDecoration.combine(
                            listOf(
                                TextDecoration.Underline,
                                TextDecoration.LineThrough,
                            ),
                        ),
                        fontSynthesis = FontSynthesis.All,
                    ),
                ) {
                    append("Underline and LineThrough Decoration")
                }
                withStyle(
                    style =
                    SpanStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontStyle = FontStyle.Italic,
                    ),
                ) {
                    append(
                        "on specific parts. Testing min and max lines parameters to see how it behaves when the text is too long and needs to be truncated or wrapped.",
                    )
                }
            },
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            minLines = 1,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextDashedStyledUnderline() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Text with Dashed Underline Decoration",
            style =
            TextStyle(
                color = Color.White,
                fontSize = 23.sp,
                fontStyle = FontStyle.Italic,
                /*
                    // Dashed underline is not directly supported,
                    but you can customize it with a custom TextDecoration implementation if needed.
                 */
            ),
            textAlign = TextAlign.Center,
            modifier =
            Modifier
                .drawBehind {
                    val strokeWidth = 4f
                    val dashLength = 10f
                    val spaceLength = 5f
                    val totalLength = dashLength + spaceLength
                    var currentLength = 0f
                    while (currentLength < size.width) {
                        drawLine(
                            color = Color.White,
                            start = Offset(currentLength, size.height),
                            end = Offset(currentLength + dashLength, size.height),
                            strokeWidth = strokeWidth,
                        )
                        currentLength += totalLength
                    }
                },
        )
    }
}
