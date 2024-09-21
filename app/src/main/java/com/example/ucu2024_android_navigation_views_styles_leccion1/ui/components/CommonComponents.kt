package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.components

import android.content.res.Resources.getSystem
import android.graphics.PointF
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.graphics.minus
import androidx.core.graphics.plus
import com.example.ucu2024_android_navigation_views_styles_leccion1.R
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.Profile
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateProfileDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.tan

@Composable
fun ImageBackgroundBox(imageResource: Int, modifier: Modifier = Modifier, content: @Composable (BoxScope.() -> Unit)) {
    val image = ImageBitmap.imageResource(id = imageResource)
    val brush = remember {
        ShaderBrush(ImageShader(
            image,
            TileMode.Repeated,
            TileMode.Repeated,
        ))
    }
    Box(
        modifier = modifier
            .background(brush)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(0.7f))
            .fillMaxSize(),
        content = content,
    )
}

@Composable
fun MaxSizedColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier
            .fillMaxSize(1f),
        verticalArrangement,
        horizontalAlignment,
        content = content
    )
}

private fun containerThemedOnSizeChanged(
    i1: MutableState<Offset>,
    i2: MutableState<Offset>,
): (IntSize) -> Unit {
    return {
        size ->
        val width = size.width.toFloat()
        val height = size.height.toFloat()

        val midpoint = Offset(width / 2, height / 2)

        val slope = (width / height)

        val x1 = Offset(
            x = 0f,
            y = midpoint.y + slope * (0f - midpoint.x)
        )

        val x2 = Offset(
            x = width,
            y = midpoint.y + slope * (width - midpoint.x)
        )

        val distanceCX1 = sqrt((midpoint.x - x1.x).pow(2) + (midpoint.y - x1.y).pow(2))

        val i1Distance = distanceCX1 * 0.01f
        val i2Distance = distanceCX1 * 0.01f

        val unitVector = Offset(
            (x2.x - x1.x) / distanceCX1,
            (x2.y - x1.y) / distanceCX1
        )

        i1.value = Offset(
            midpoint.x - unitVector.x * i1Distance,
            midpoint.y - unitVector.y * i1Distance
        )

        i2.value = Offset(
            midpoint.x + unitVector.x * i2Distance,
            midpoint.y + unitVector.y * i2Distance
        )
    }
}

@Composable
fun CardContainerThemed(modifier: Modifier = Modifier, content: @Composable (ColumnScope.() -> Unit)) {
    val i1 = remember { mutableStateOf(Offset.Zero) }
    val i2 = remember { mutableStateOf(Offset.Infinite) }
    val cornerRadius = 10.dp

    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier
            .onSizeChanged(containerThemedOnSizeChanged(i1, i2))
            .shadow(2.dp, shape = RoundedCornerShape(cornerRadius))
            .border(
                2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.Black.copy(alpha = 0.3f),
                    ),
                    start = i1.value,
                    end = i2.value,
                    tileMode = TileMode.Clamp,
                ),
                shape = RoundedCornerShape(cornerRadius)
            ),
        content = content,
    )
}

@Composable
fun CardSectionContainerThemed(modifier: Modifier = Modifier, content: @Composable (ColumnScope.() -> Unit)) {
    val i1 = remember { mutableStateOf(Offset.Zero) }
    val i2 = remember { mutableStateOf(Offset.Infinite) }
    val cornerRadius = 8.dp

    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.8f).compositeOver(Color.Black),
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        modifier = modifier
            .onSizeChanged(containerThemedOnSizeChanged(i1, i2))
            .border(
                2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.2f),
                        Color.White.copy(alpha = 0.1f),
                    ),
                    start = i1.value,
                    end = i2.value,
                    tileMode = TileMode.Clamp,
                ),
                shape = RoundedCornerShape(cornerRadius)
            ),
        content = content,
    )
}

@Composable
fun OutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    fillColor: Color = Color.Unspecified,
    outlineColor: Color,
    fontSize: TextUnit = TextUnit.Unspecified,
    //fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    //fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    outlineDrawStyle: Stroke = Stroke(),
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.clearAndSetSemantics {  },
            color = outlineColor,
            fontSize = fontSize,
            //fontStyle = fontStyle,
            fontWeight = fontWeight,
            //fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = null,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style.copy(
                shadow = null,
                drawStyle = outlineDrawStyle,
            ),
        )

        Text(
            text = text,
            color = fillColor,
            fontSize = fontSize,
            //fontStyle = fontStyle,
            fontWeight = fontWeight,
            //fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style,
        )
    }
}

@Composable
fun OutlinedTitleText(text: String, modifier: Modifier = Modifier) {
    OutlinedText(
        text = text,
        modifier = modifier,
        fillColor = Color.White,
        outlineColor = Color.Black,
        style = MaterialTheme.typography.titleLarge,
        outlineDrawStyle = Stroke(
            miter = 10f,
            width = 20f,
            join = StrokeJoin.Round
        )
    )
}

@Composable
fun OutlineSubTitleText(text: String, modifier: Modifier = Modifier) {
    OutlinedText(
        text = text,
        modifier = modifier,
        fillColor = Color.White,
        outlineColor = Color.Black,
        style = MaterialTheme.typography.titleMedium,
        outlineDrawStyle = Stroke(
            miter = 10f,
            width = 15f,
            join = StrokeJoin.Round
        )
    )
}


@Composable
fun StackableIconsRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy((-15).dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(end = 20.dp),
        content = content,
    )
}

@Composable
fun StackableIconsRowItem(resource: Int, description: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = description,
        modifier = modifier
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.surface), CircleShape)
            .clip(CircleShape)
            .size(45.dp)
    )
}

@Composable
fun StackableIconsRowCounter(count: Int, modifier: Modifier = Modifier) {
    Badge(
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        modifier = modifier
            .size(35.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.surface), CircleShape)
    ) {
        Text(text = "$count")
    }
}

@Composable
fun OutlinedIconButtonThemed(
    iconResource: Int,
    description: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
    ) {
        val colors = IconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            disabledContentColor = MaterialTheme.colorScheme.outline,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        )
        OutlinedIconButton(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            enabled = enabled,
            colors = colors,
            border = BorderStroke(2.dp, if (enabled) colors.contentColor else colors.disabledContentColor),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight(),
        ) {
            Icon(painter = painterResource(id = iconResource), contentDescription = description)
        }
    }
}

@Composable
fun TextButtonThemed(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        TextButton(
            onClick = onClick,
            shape = RoundedCornerShape(5.dp),
            enabled = enabled,
            colors = ButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                disabledContainerColor = MaterialTheme.colorScheme.outlineVariant,
            ),
            modifier = modifier
                .padding(10.dp),
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 3.dp, horizontal = 10.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

private val Dp.float: Float get() = this.value * getSystem().displayMetrics.density

class BackButtonShape(
    val cornerRadius: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            val x = size.width
            val y = size.height
            val roundRadius = cornerRadius.float

            val origin = PointF(0f,0f)
            val topSlope = 0
            val leftSlope = y/(x*0.3f)
            val topLeftAngle = atan((leftSlope - topSlope) /(1 + topSlope*leftSlope))
            val bottomLeftAngle = atan((topSlope - leftSlope) /(1 + topSlope*leftSlope))

            val projectOnLine: (Float, Float, PointF) -> PointF = { distance, slope, start ->
                val point = PointF(
                    sqrt((distance*distance)/((slope*slope)+1)),
                    sqrt((distance*distance)/((1/(slope*slope))+1))
                )
                if (distance < 0)
                    start.minus(point)
                else
                    start.plus(point)
            }

            val degToRadians: (Float) -> Float = { degrees -> degrees * PI.toFloat() / 180 }

            val C1 = PointF(roundRadius / tan(topLeftAngle/2), 0f)
            val O2 = PointF(x*0.8f - y*0.5f, y*0.5f)
            val C6 = PointF((x*0.3f)+(-roundRadius / tan(bottomLeftAngle)), y)
            val O3 = PointF(C6.x, C6.y - roundRadius)
            val O1 = PointF(C1.x,roundRadius)

            moveTo(C1.x,C1.y)
            arcToRad(Rect(Offset(O2.x, O2.y), y*0.5f), -PI.toFloat()/2, PI.toFloat(), false)
            arcToRad(Rect(Offset(O3.x,O3.y), roundRadius), PI.toFloat()/2, (PI.toFloat() - bottomLeftAngle)/4, false)
            arcToRad(Rect(Offset(O1.x,O1.y), roundRadius), (topLeftAngle)+(PI.toFloat()/2), (PI.toFloat() - topLeftAngle),false)
        }
    )
}

@Composable
fun BackButtonThemed(iconResource: Int, description: String, onClick: () -> Unit = {}) {
    ElevatedButton(
        onClick = onClick,
        shape = BackButtonShape(10.dp),
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.error,
            containerColor = MaterialTheme.colorScheme.scrim,
            disabledContentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = MaterialTheme.colorScheme.scrim,
        ),
        border = BorderStroke(
            width = 4.dp,
            color = MaterialTheme.colorScheme.error,
        ),
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = description,
            modifier = Modifier
        )
    }
}

@Composable
fun UserPlaque(profile: Profile, onClick: (() -> Unit)? = null) {
    val modifier = if (onClick == null) Modifier else Modifier.clickable {
        onClick()
    }

    RaisedContainer(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(5.dp)
                .requiredWidth(200.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(start = 20.dp, end = 10.dp)
            ) {
                UserPlaqueNameTag("${profile.name} ${profile.lastName}", profile.email)
            }
            UserPlaqueAvatar(profile.avatar)
        }
    }
}

@Composable
fun TopNavButton(iconResource: Int, description: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val i1 = remember { mutableStateOf(Offset.Zero) }
    val i2 = remember { mutableStateOf(Offset.Infinite) }

    IconButton(
        modifier = modifier
            .onSizeChanged(containerThemedOnSizeChanged(i1, i2))
            .shadow(2.dp, shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh, shape = CircleShape)
            .border(
                2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.Black.copy(alpha = 0.3f),
                    ),
                    start = i1.value,
                    end = i2.value,
                    tileMode = TileMode.Clamp,
                ),
                shape = CircleShape
            ),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = description,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun RaisedContainer(modifier: Modifier = Modifier, content: @Composable (RowScope.() -> Unit)) {
    val i1 = remember { mutableStateOf(Offset.Zero) }
    val i2 = remember { mutableStateOf(Offset.Infinite) }

    Row(
        modifier = modifier
            .onSizeChanged(containerThemedOnSizeChanged(i1, i2))
            .shadow(2.dp, shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh, shape = CircleShape)
            .border(
                2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.Black.copy(alpha = 0.3f),
                    ),
                    start = i1.value,
                    end = i2.value,
                    tileMode = TileMode.Clamp,
                ),
                shape = CircleShape
            ),
        content = content,
    )
}

@Composable
private fun UserPlaqueAvatar(imgResource: Int) {
    Image(
        painter = painterResource(id = imgResource),
        contentDescription = "Profile",
        modifier = Modifier
            .size(40.dp)
            .border(
                3.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.2f),
                        Color.White.copy(alpha = 0.1f),
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                    tileMode = TileMode.Clamp,
                ),
                shape = CircleShape
            )
            .border(
                BorderStroke(3.dp, MaterialTheme.colorScheme.surfaceContainer),
                CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
            .size(40.dp),
    )
}

@Composable
private fun UserPlaqueNameTag(userName: String, userEmail: String) {
    Text(text = userName, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelMedium)
    Text(text = userEmail, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelSmall)
}

@Composable
fun AlertDialogThemed(title: String, text: String, confirmLabel: String = "Confirm", onConfirm: () -> Unit = {}, dismissLabel: String = "Cancel", onDismissRequest: () -> Unit = {}) {
    val commonModifier = Modifier.height(IntrinsicSize.Min)
    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(10.dp),
        title = { Text(text = title) },
        text = { Text(text = text) },
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        confirmButton = {
            TextButtonThemed("Si, cerrar sesión", onClick = onConfirm, modifier = commonModifier)
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest, modifier = Modifier.padding(vertical = 10.dp)) {
                Text(text = "Cancelar")
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun BackButtonThemedPreview() {
    BackButtonThemed(R.drawable.baseline_logout_24, "Logout")
}

@Preview(showBackground = true)
@Composable
fun UserPlaquePreviewDark() {
    val profile = generateProfileDummyData()[0]
    AppTheme(
        darkTheme = true
    ) {
        UserPlaque(profile)
    }
}

@Preview(showBackground = true)
@Composable
fun UserPlaquePreviewLight() {
    val profile = generateProfileDummyData()[0]
    AppTheme(
        darkTheme = false
    ) {
        UserPlaque(profile)
    }
}

@Preview(showBackground = true)
@Composable
fun TopNavButtonPreviewDark() {
    val profile = generateProfileDummyData()[0]
    AppTheme(
        darkTheme = true
    ) {
        TopNavButton(R.drawable.baseline_logout_24, "Profile")
    }
}

@Preview(showBackground = true)
@Composable
fun TopNavButtonPreviewLight() {
    val profile = generateProfileDummyData()[0]
    AppTheme(
        darkTheme = false
    ) {
        TopNavButton(R.drawable.baseline_logout_24, "Profile")
    }
}
