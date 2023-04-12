package example.backcontrolefacile.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
