package services.interfaces;

import model.Product;
import model.Store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MiscellaneousOperations {
    default void createAFileToSaveData(String filePath, String fileContent) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileContent);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default List<Product> viewProductByCategory(Store store, String category) {
        List<Product> productArrayList = new ArrayList<>();
        for (int i = 0; i < store.getListOfProductInStore().length; i++) {
            Product product = store.getListOfProductInStore()[i];
            if (product.getProductCategory().equalsIgnoreCase(category))
                productArrayList.add(product);
        }
        return productArrayList;
    }
}
