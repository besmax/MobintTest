package bes.max.mobinttest.companies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import bes.max.mobinttest.R
import bes.max.mobinttest.companies.domain.models.Company
import bes.max.mobinttest.companies.presentation.CompaniesViewModel
import bes.max.mobinttest.core.ui.theme.Black
import bes.max.mobinttest.core.ui.theme.Blue
import bes.max.mobinttest.core.ui.theme.LightGray
import bes.max.mobinttest.core.ui.theme.SegoeFontFamily
import bes.max.mobinttest.core.ui.theme.White
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompanyCardsScreen(
    viewModel: CompaniesViewModel = koinViewModel()
) {

    val companies = viewModel.companiesPagerFlow.collectAsLazyPagingItems()

    val snackbarHostState = remember { SnackbarHostState() }
    val localCoroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
                .background(color = LightGray)
        ) {
            Title()
            CompanyList(
                companies = companies,
                onEyeIconClick = { text ->
                    localCoroutineScope.launch {
                        snackbarHostState.showSnackbar(text)
                    }
                },
                onDeleteIconClick = { text ->
                    localCoroutineScope.launch {
                        snackbarHostState.showSnackbar(text)
                    }
                },
                onMoreButtonClick = { text ->
                    localCoroutineScope.launch {
                        snackbarHostState.showSnackbar(text)
                    }
                },
            )
        }
    }

}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(dimensionResource(id = R.dimen.margin1)),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.manage_cards),
            fontFamily = SegoeFontFamily,
            fontSize = dimensionResource(id = R.dimen.text_title_size).value.sp,
            textAlign = TextAlign.Center,
            color = Blue
        )
    }
}

@Composable
fun CompanyItem(
    company: Company,
    onEyeIconClick: (String) -> Unit,
    onDeleteIconClick: (String) -> Unit,
    onMoreButtonClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.margin1) / 2),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(company.cardBackgroundColor))
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.margin1),
                    end = dimensionResource(id = R.dimen.margin1),
                    top = dimensionResource(id = R.dimen.margin1),
                    bottom = dimensionResource(id = R.dimen.margin2),
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = company.companyName,
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text_title_size).value.sp,
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor(company.highlightTextColor))
            )

            AsyncImage(
                model = company.logo,
                contentDescription = "${company.companyName} logo",
                modifier = Modifier
                    .size(dimensionResource(R.dimen.logo_size))
                    .clip(RoundedCornerShape(100.dp)),
            )
        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.margin1))
                .fillMaxWidth()
                .height(1.dp)
                .background(color = LightGray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.margin1)),
        ) {
            Text(
                text = company.loyaltyLevelMark.toString(),
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text0).value.sp,
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor(company.highlightTextColor))
            )

            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin2)))

            Text(
                text = stringResource(id = R.string.points),
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text0).value.sp,
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor(company.textColor))
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.margin1)),
        ) {
            Text(
                text = stringResource(id = R.string.cashback),
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text3).value.sp,
                textAlign = TextAlign.Start,
                color = Color(android.graphics.Color.parseColor(company.textColor))
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin3)))

            Text(
                text = stringResource(id = R.string.level),
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text3).value.sp,
                textAlign = TextAlign.Start,
                color = Color(android.graphics.Color.parseColor(company.textColor))
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.margin1),
                    vertical = dimensionResource(id = R.dimen.margin2)
                ),
        ) {
            Text(
                text = stringResource(
                    id = R.string.cashback_placeholder,
                    company.loyaltyLevelNumber
                ),
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text2).value.sp,
                textAlign = TextAlign.Start,
                color = Color(android.graphics.Color.parseColor(company.highlightTextColor))
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin3)))

            Text(
                text = company.loyaltyLevelName,
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text2).value.sp,
                textAlign = TextAlign.Start,
                color = Color(android.graphics.Color.parseColor(company.highlightTextColor))
            )
        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.margin1))
                .fillMaxWidth()
                .height(1.dp)
                .background(color = LightGray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = dimensionResource(id = R.dimen.margin1),
                    start = dimensionResource(id = R.dimen.margin2),
                    end = dimensionResource(id = R.dimen.margin2)
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val context = LocalContext.current
            IconButton(onClick = {
                onEyeIconClick(
                    context.resources.getString(
                        R.string.show_icon_dialog,
                        company.companyId
                    )
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = "Eye icon",
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.icon_size)),
                    tint = Color(android.graphics.Color.parseColor(company.mainColor))
                )
            }

            IconButton(onClick = {
                onDeleteIconClick(
                    context.resources.getString(
                        R.string.delete_icon_dialog,
                        company.companyId
                    )
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = "Eye icon",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                    tint = Color(android.graphics.Color.parseColor(company.accentColor))
                )
            }

            Button(
                onClick = {
                    onMoreButtonClick(
                        context.resources.getString(
                            R.string.more_button_dialog,
                            company.companyId
                        )
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor(company.backgroundColor))
                ),
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.margin1))
            ) {
                Text(
                    text = stringResource(id = R.string.more_details),
                    fontFamily = SegoeFontFamily,
                    fontSize = dimensionResource(id = R.dimen.text2).value.sp,
                    textAlign = TextAlign.Start,
                    color = Color(android.graphics.Color.parseColor(company.mainColor))
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyList(
    companies: LazyPagingItems<Company>,
    onEyeIconClick: (String) -> Unit,
    onDeleteIconClick: (String) -> Unit,
    onMoreButtonClick: (String) -> Unit,
) {
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            companies.refresh()
            delay(1000)
            state.endRefresh()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .nestedScroll(state.nestedScrollConnection),
    ) {

        if (companies.loadState.refresh is LoadState.Loading) {
            LoadingCompanies()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(companies) { company ->
                    if (company != null) {
                        CompanyItem(
                            company = company,
                            onEyeIconClick,
                            onDeleteIconClick,
                            onMoreButtonClick
                        )
                    }
                }

                item {
                    if (companies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        if (state.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                state = state,
                contentColor = Blue,
                containerColor = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun LoadingCompanies() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_preloader))
                .align(Alignment.Center),
            color = Black
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin2)))

        Text(
            text = stringResource(id = R.string.loading_companies),
            fontFamily = SegoeFontFamily,
            fontSize = dimensionResource(id = R.dimen.text_preloader_size).value.sp,
            textAlign = TextAlign.Center,
            color = Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDialog(
    onDismissRequest: () -> Unit,
    text: String
) {
    BasicAlertDialog(onDismissRequest = { onDismissRequest() }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text,
                fontFamily = SegoeFontFamily,
                fontSize = dimensionResource(id = R.dimen.text_title_size).value.sp,
                textAlign = TextAlign.Center,
                color = Black
            )
        }
    }
}