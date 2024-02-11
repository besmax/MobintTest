package bes.max.mobinttest.companies.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import bes.max.mobinttest.companies.domain.models.Company
import bes.max.mobinttest.companies.presentation.CompaniesViewModel
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompanyCardsScreen(
    viewModel: CompaniesViewModel = koinViewModel()
) {

    val companies = viewModel.companiesPagerFlow.collectAsLazyPagingItems()


    CompanyList(companies)

}

@Composable
fun CompanyItem(company: Company) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(company.mainColor))
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = company.companyName,
                fontSize = 16.sp
            )

            AsyncImage(
                model = company.logo,
                contentDescription = "${company.companyName} logo",
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(2.dp)),
            )
        }
    }
}

@Composable
fun CompanyList(companies: LazyPagingItems<Company>) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        if (companies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
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