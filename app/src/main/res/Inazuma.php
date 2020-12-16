<?php

$server = "localhost";
$user = "root";
$pass = "clave";
$bd = "BDManuel";

//Creamos la conexión
$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");

//generamos la consulta
$sql = "SELECT * FROM InazumaEleven";
mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

if(!$result = mysqli_query($conexion, $sql)) die();

$equipo = array(); //creamos un array

while($row = mysqli_fetch_array($result))
{
    $nombre=$row['Nombre'];
    $apellido=$row['Apellido'];
    $dorsal=$row['Dorsal'];
    $image=$row['Imagen'];


    $equipo[] = array('Nombre'=> $nombre, 'Apellido'=> $apellido, 'Dorsal'=> $dorsal,'Imagen'=>$image);

}

//desconectamos la base de datos
$close = mysqli_close($conexion)
or die("Ha sucedido un error inexperado en la desconexion de la base de datos");


//Creamos el JSON
$json_string = json_encode($equipo);

echo $json_string;

//Si queremos crear un archivo json, sería de esta forma:
/*
$file = 'clientes.json';
file_put_contents($file, $json_string);
*/


?>