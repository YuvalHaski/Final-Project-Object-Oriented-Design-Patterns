package comparators;

import products.Product;
import java.util.Comparator;

public class LexicographicComparatorProduct implements Comparator<Product>{
	  @Override
      public int compare(Product p1, Product p2) {
          return p1.getCatalogNum().compareTo(p2.getCatalogNum());
      }
}
