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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
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
import bes.max.mobinttest.core.ui.theme.DarkGray
import bes.max.mobinttest.core.ui.theme.LightGray
import bes.max.mobinttest.core.ui.theme.SegoeFontFamily
import bes.max.mobinttest.core.ui.theme.White
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompanyCardsScreen(
    viewModel: CompaniesViewModel = koinViewModel()
) {

    val companies = viewModel.companiesPagerFlow.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGray)
    ) {
        Title()
        CompanyList(companies)
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
fun CompanyItem(company: Company) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.margin1)),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(company.mainColor))
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.margin1)),
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
                    .clip(RoundedCornerShape(2.dp)),
            )
        }

        Spacer(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.margin1))
                .fillMaxWidth()
                .height(1.dp)
                .background(color = DarkGray)

        )

        Row(
            modifier = Modifier.fillMaxWidth()
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
    }
}

@Composable
fun CompanyList(companies: LazyPagingItems<Company>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
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
                        CompanyItem(company = company)
                    }
                }

                item {
                    if (companies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
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