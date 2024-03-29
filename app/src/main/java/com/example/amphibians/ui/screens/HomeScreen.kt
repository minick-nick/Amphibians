package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianInfo
import com.example.amphibians.ui.theme.AmphibiansTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier = Modifier
) {
    when(amphibiansUiState) {
        is AmphibiansUiState.Loading -> Text("Loading...")
        is AmphibiansUiState.Error -> Text("Error!")
        is AmphibiansUiState.Success -> AmphibiansList(amphibiansUiState.amphibians)
    }
}

@Composable
fun AmphibiansList(
    amphibians: List<AmphibianInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(amphibians) {
            amphibian ->
            AmphibianCard(
                amphibianInfo = amphibian,
                modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
            )
        }
    }
}

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    amphibianInfo: AmphibianInfo
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = amphibianInfo.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.large_padding))
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibianInfo.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.amphibian_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = amphibianInfo.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.large_padding))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansListPreview() {
    AmphibiansTheme {
        val mockData = listOf(
            AmphibianInfo(
                name = "Amphibian Name",
                type = "",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae dolor quis massa vestibulum molestie quis sed sapien. Morbi risus magna, tempus ut ante at, ullamcorper cursus nibh. Morbi condimentum magna et mauris sollicitudin dictum. Mauris eu velit ut ante vulputate rutrum. Integer sagittis lectus quam, vitae accumsan velit aliquet eu. Vestibulum at ex vel elit ullamcorper sollicitudin sit amet at justo. Proin sit amet efficitur sem, at efficitur lectus. Vivamus interdum laoreet risus, ac auctor nunc hendrerit eu. Ut tincidunt libero eleifend, porttitor augue et, tempor neque. Donec est velit, commodo ac elit eget, faucibus mollis eros.",
                imgSrc = ""
            ),
            AmphibianInfo(
                name = "Amphibian Name",
                type = "",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae dolor quis massa vestibulum molestie quis sed sapien. Morbi risus magna, tempus ut ante at, ullamcorper cursus nibh. Morbi condimentum magna et mauris sollicitudin dictum. Mauris eu velit ut ante vulputate rutrum. Integer sagittis lectus quam, vitae accumsan velit aliquet eu. Vestibulum at ex vel elit ullamcorper sollicitudin sit amet at justo. Proin sit amet efficitur sem, at efficitur lectus. Vivamus interdum laoreet risus, ac auctor nunc hendrerit eu. Ut tincidunt libero eleifend, porttitor augue et, tempor neque. Donec est velit, commodo ac elit eget, faucibus mollis eros.",
                imgSrc = ""
                )
        )
        AmphibiansList(amphibians = mockData)
    }
}

@Preview()
@Composable
fun AmphibianCardPreview() {
    AmphibiansTheme {
        val mockData = AmphibianInfo(
            name = "Amphibian Name",
            type = "",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae dolor quis massa vestibulum molestie quis sed sapien. Morbi risus magna, tempus ut ante at, ullamcorper cursus nibh. Morbi condimentum magna et mauris sollicitudin dictum. Mauris eu velit ut ante vulputate rutrum. Integer sagittis lectus quam, vitae accumsan velit aliquet eu. Vestibulum at ex vel elit ullamcorper sollicitudin sit amet at justo. Proin sit amet efficitur sem, at efficitur lectus. Vivamus interdum laoreet risus, ac auctor nunc hendrerit eu. Ut tincidunt libero eleifend, porttitor augue et, tempor neque. Donec est velit, commodo ac elit eget, faucibus mollis eros.",
            imgSrc = ""
        )
        AmphibianCard(amphibianInfo = mockData)
    }
}
