package com.gregzenkov.aichallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.pages.ChildPages
import com.arkivanov.decompose.extensions.compose.pages.PagesScrollAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.pages.ChildPages as ChildPagesValue
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.gregzenkov.aichallenge.component.*
import com.gregzenkov.aichallenge.ui.theme.AIChallengeTheme

/**
 * UI для компонента постраничной навигации
 */
@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun PageNavigationContent(
    component: PageNavigationComponent,
    modifier: Modifier = Modifier
) {
    val pages by component.pages.subscribeAsState()

    Column(modifier = modifier.fillMaxSize()) {
        // Индикатор страниц
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pages.items.size) { index ->
                PageIndicator(
                    isSelected = index == pages.selectedIndex,
                    onClick = { component.selectPage(index) }
                )
                if (index < pages.items.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        // Контент страниц с анимацией
        ChildPages(
            pages = component.pages,
            onPageSelected = component::selectPage,
            scrollAnimation = PagesScrollAnimation.Default,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { _, page ->
            when (page) {
                is PageNavigationComponent.Page.Home -> HomePageContent(page.component)
                is PageNavigationComponent.Page.Profile -> ProfilePageContent(page.component)
                is PageNavigationComponent.Page.Settings -> SettingsPageContent(page.component)
            }
        }
    }
}

@Composable
private fun PageIndicator(
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(if (isSelected) 12.dp else 8.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                shape = RoundedCornerShape(50)
            )
    )
}

@Composable
private fun HomePageContent(
    component: HomePageComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = component.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Свайп влево для перехода на следующую страницу",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
private fun ProfilePageContent(
    component: ProfilePageComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = component.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Имя: Пользователь", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Email: user@example.com", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun SettingsPageContent(
    component: SettingsPageComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = component.title,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Уведомления", style = MaterialTheme.typography.bodyLarge)
                    Switch(checked = true, onCheckedChange = {})
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Темная тема", style = MaterialTheme.typography.bodyLarge)
                    Switch(checked = false, onCheckedChange = {})
                }
            }
        }
    }
}
