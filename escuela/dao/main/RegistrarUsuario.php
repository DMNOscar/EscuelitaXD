<?php
	require_once(dirname(__FILE__)."/../../core/utils.php");
	require_once(dirname(__FILE__)."/../../library/nusoap/lib/nusoap.php");
	
	$bd = connectBD();
	$usuarioID = $_REQUEST['nombre'];
	$nick 	 = $_REQUEST['nick'];
	$pass    = $_REQUEST['pass'];
	$categoria_id = 1;
	
	//Encriptamos el pass
	$cliente = new nusoap_client("http://atc.mx/android/libraryPHP/encriptado/seguridad.php?wsdl", true);
	$parametros = array ('password' => $pass);
	$passEncriptado = $cliente -> call('encriptar', $parametros);
	
	$consulta= "INSERT INTO usuario(nombre, nick, pass, categoria_id) VALUES ('".$usuarioID."','".$nick."','".$passEncriptado."','".$categoria_id."');";
				

	$resultado = $bd->query($consulta);
	
	if($resultado){
			echo "registrado";
	}
	
?>