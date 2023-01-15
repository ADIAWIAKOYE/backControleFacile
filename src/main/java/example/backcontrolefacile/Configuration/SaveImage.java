package example.backcontrolefacile.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class SaveImage {

    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "controleFacile/images/utilisateur/";
    public static String servervehicule = localhost + "controleFacile/images/vehicule/";
    public static String serverpermis = localhost + "controleFacile/images/permis/";

    public static String Vehiculelocation = "C:/xampp/htdocs/controleFacile/images/vehicule/";
    public static String Userlocation = "C:/xampp/htdocs/controleFacile/images/utilisateur/";
    public static String Permislocation = "C:/xampp/htdocs/controleFacile/images/permis/";

    public static String save(String typeImage, MultipartFile file, String fileName) {
        String src = "";
        String server = "";
        String location = "";
        if (typeImage == "user") {
            location = Userlocation;
            server = serveruser;
        } else if (typeImage == "vehicule"){
            location = Vehiculelocation;
            server = servervehicule;

        }else {
            location = Permislocation;
            server = serverpermis;

        }

        /// debut de l'enregistrement
        /*try {
            int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(location);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier+ file.getOriginalFilename().substring(index).toLowerCase()));
                src = server + nomFichier
                        + file.getOriginalFilename().substring(index).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier
                        + file.getOriginalFilename().substring(index).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier
                            + file.getOriginalFilename().substring(index).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrèe

                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier
                            +file.getOriginalFilename().substring(index).toLowerCase();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

            src = null;
        }

        return src;*/


        try {
            Path filePath = Paths.get(location + fileName);

            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.copy(file.getInputStream(), filePath);
                 src = server + fileName ;
            } else {
                Files.delete(filePath);
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName ;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle exception
            src = null;
        }

        return src;
    }



}
