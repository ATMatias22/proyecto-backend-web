package com.sensor.utils.directory;

import com.sensor.utils.file.FileHelper;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Getter
public class DirectoryHandler {


    //lo necesitamos para saber en que ruta vamos a guardar todas las subrutas que haremos aca
    private final String mainDirectory;

    //necesietamos el Id para crear el directorio y usarlo como parte del nombre de la imagen
    private Integer id;

    // a traves del mainDirectory y el id crearemos este directory en donde se guardara todo lo que haremos
    //en caso de eliminar este directory sera donde apunta la imagen guardada
    private File directory;

    //en esta ruta guardamos la ruta del antiguo archivo existente, para borrarlo y dejar el atributo directory con la ruta nueva que debe crear.
    private File directoryForDeleteBeforeCreate;

    //con este atributo podremos saber si debemos borrar la ruta que tiene el directory
    private boolean isNecessaryDelete;

    //con este atributo podremos saber si debemos crear la ruta que tiene el directory
    private boolean isNecessaryCreate;

    //esta es la antigua ruta donde se guardaba la imagen, puede ser nulo, que significa que no habia iamgen
    //este atributo no contiene la ruta raiz, solo una parte
    private final String oldPath;

    //en este atributo se guardara el nuevo path que se guardara en la base de datos
    //como se menciono anteriormente, en la base de datos solo guardamos una parte de la ruta
    private String newPathForDB;

    //aca se guardara el nuevo nombre de la imagen
    private String newNamePicture;

    //este atributo es necesario para el nuevo nombre de la imagen
    //todas las imagenes tendran como nombre, el prefijo + su id
    private final String prefix;

    //Aca esta el archivo que se guardara en el directorio especificado
    private final MultipartFile filePicture;


    public DirectoryHandler(Integer id, String oldPath, String prefix, MultipartFile filePicture, String mainDirectory) {
        this.id = id;
        this.oldPath = oldPath;
        this.prefix = prefix;
        this.filePicture = filePicture;
        this.mainDirectory = mainDirectory;
        this.directory = null;
        this.isNecessaryDelete = false;
        this.isNecessaryCreate = false;
        this.newPathForDB = null;
        this.newNamePicture = null;
        this.directoryForDeleteBeforeCreate = null;
    }


    public void ifIsNecessaryDeleteDirectoryThenDoIt() {
        if(this.directoryForDeleteBeforeCreate != null){
            FileHelper.deleteDirectory(directoryForDeleteBeforeCreate);
        }

        if (this.isNecessaryDelete) {
            FileHelper.deleteDirectory(directory);
        }
    }

    public void ifIsNecessaryCreateDirectoryThenDoIt() throws IOException {
        if (this.isNecessaryCreate) {
            FileHelper.saveFile(filePicture, directory, newNamePicture);
        }
    }

    public void ifIsNecessaryCreateOrDeleteThenDoIt() throws IOException {
        this.ifIsNecessaryDeleteDirectoryThenDoIt();
        this.ifIsNecessaryCreateDirectoryThenDoIt();
    }


    public void prepareDirectoryForModify() {
        if (this.filePicture.isEmpty()) {
            //si habia una imagen anteriormente
            if (this.oldPath != null) {
                //obtenemos la ruta de donde esta la imagen.
                this.directory = new File(this.mainDirectory + this.oldPath);

                //seteamos null porque como no va a existir archivo, no hay ruta de la imagen guardada.
                this.newPathForDB = null;

                //le decimos al manejador que debe eliminar la ruta.
                this.isNecessaryDelete = true;
            }
            //si hay un archivo en la request
        } else {
            //seteamos el directorio en donde se guardara la imagen
            this.directory = new File(this.mainDirectory + this.id);

            //renombramos la imagen
            this.newNamePicture = FileHelper.renameFile(this.filePicture, this.id, this.prefix);

            //creamos el directorio en donde estara la imagen
            FileHelper.createDirectory(directory);

            // armamos la ruta que se guardara en la base de datos
            this.newPathForDB = this.assembleDirectoryNameForSaveInDataBase(this.id, this.newNamePicture);

            // y  hay una imagen guardada anteriormente
            if (this.oldPath != null) {
                //colocamos la ruta de donde estaba el archivo anterior, para posteriormente borrarlo
                this.directoryForDeleteBeforeCreate = new File(this.mainDirectory + this.oldPath);
            }

            //le decimos al handler que tiene que crear el directorio, y colocar la imagen.
            this.isNecessaryCreate = true;
        }
    }

    public void prepareDirectoryForSave() {
        this.directory = new File(mainDirectory + id); //CREAMOS DIRECTORIO
        FileHelper.createDirectory(directory);
        if (!this.filePicture.isEmpty()) {
            this.newNamePicture = FileHelper.renameFile(filePicture, id, prefix);
            this.newPathForDB = this.assembleDirectoryNameForSaveInDataBase(id, this.newNamePicture);
            this.isNecessaryCreate = true;
        }else{
            this.newPathForDB = null;
        }
    }

    private String assembleDirectoryNameForSaveInDataBase(Integer id, String nameImage) {
        return id + File.separator + nameImage;
    }
}
