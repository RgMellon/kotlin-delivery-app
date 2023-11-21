
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.sampledata.sampleProducts
import com.example.delivery.model.Product
import com.example.delivery.ui.theme.DeliveryTheme

@Composable
fun ProductItemSection(title: String, products: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = title, modifier = Modifier
                .padding(end = 16.dp, start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding =  PaddingValues(horizontal = 16.dp)
        ) {
            items(products) {
                product -> ProductItem(product = product)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProductItemSectionPrev() {
   DeliveryTheme {
       Surface {
           ProductItemSection("Promocões", products = sampleProducts)
       }
   }
}

