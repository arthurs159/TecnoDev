package files;

import tecnodev.category.Category;
import validator.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static validator.Validator.*;

public class ReadFiles {

    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream arquivoCategoria = new FileInputStream(
                new File("/home/arthur/Alura/planilha-dados-escola - Categoria.csv"));

        Scanner lerArquivo = new Scanner(arquivoCategoria, StandardCharsets.UTF_8);
        lerArquivo.nextLine(); // Ignora a primeira linha

        List<Category> listaCategoria = new ArrayList<>();

        while (lerArquivo.hasNextLine()) {
            String line = lerArquivo.nextLine();

            String[] data = line.split(",");

            Category categoria = new Category(
                    data[0],
                    data[1],
                    data[2].equals("") ? 0 : Integer.parseInt(data[2]),
                    data[3],
                    transformBoolean(data[4]),
                    data[5],
                    data[6]);

            listaCategoria.add(categoria);
        }

        for (Category categoria : listaCategoria) {
            System.out.println(categoria);
        }

    }
}
