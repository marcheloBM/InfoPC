// See https://aka.ms/new-console-template for more information
using System.Diagnostics;
using System.Globalization;
using System.Text.RegularExpressions;

//Console.WriteLine("Hello, World!");
string marca = EjecutarComandoWMIC("/c wmic computersystem get name");
marca = Regex.Replace(marca, @"\s*$", "");
Console.WriteLine(marca);

string modelo = EjecutarComandoWMIC("/c wmic csproduct get name");
modelo = Regex.Replace(modelo, @"\s*$", "");
Console.WriteLine(modelo);

string numSerie = EjecutarComandoWMIC("/c wmic bios get serialnumber");
string numSerie2 = EjecutarComandoWMIC("/c wmic path win32_computersystemproduct get uuid");
numSerie = Regex.Replace(numSerie, @"\s*$", "");
numSerie2 = Regex.Replace(numSerie2, @"\s*$", "");
string reps = numSerie;
if (reps.Equals("To be filled by O.E.M."))
{
    Console.WriteLine(numSerie);
}
else
{
    Console.WriteLine(numSerie2);
}   
//Console.WriteLine(numSerie);
//Console.WriteLine(numSerie2);

string fechaInsta = EjecutarComandoWMIC("/c wmic os get installdate");
//fechaInsta = Regex.Replace(fechaInsta, @"\s*$", "");
// Ejecutar el comando WMIC para obtener la fecha de instalación
string installDateWMIC = EjecutarComandoWMIC("/c wmic os get installdate");
string installDate = ExtractInstallDate(installDateWMIC);
// Convertir la fecha a un formato legible
DateTime fechaConvertida = DateTime.ParseExact(installDate.Substring(0, 14), "yyyyMMddHHmmss", CultureInfo.InvariantCulture);
int offsetMinutes = int.Parse(installDate.Substring(22, 3));
fechaConvertida = fechaConvertida.AddMinutes(-offsetMinutes); // Ajuste de zona horaria
Console.WriteLine("Fecha: "+fechaConvertida.ToString("yyyy-MM-dd HH:mm:ss"));

string arqui = EjecutarComandoWMIC("/c wmic os get osarchitecture");
arqui = Regex.Replace(arqui, @"\s*$", "");
Console.WriteLine(arqui);

// Espera la entrada del usuario antes de cerrar la consola
Console.WriteLine("Presiona Enter para salir...");
Console.ReadLine();

static string EjecutarComandoWMIC(string comando)
{
    // Crear una nueva instancia de la clase Process
    Process process = new Process();

    // Configurar los parámetros del proceso
    process.StartInfo.FileName = "cmd.exe";
    process.StartInfo.Arguments = comando;
    process.StartInfo.RedirectStandardOutput = true;
    process.StartInfo.UseShellExecute = false;
    process.StartInfo.CreateNoWindow = true;

    // Iniciar el proceso y esperar a que finalice
    process.Start();
    process.WaitForExit();

    // Leer la salida del comando
    string output = process.StandardOutput.ReadToEnd();
    return output;
}
static string ExtractInstallDate(string wmicOutput)
{
    // Extrae la fecha de instalación del output de WMIC
    string[] lines = wmicOutput.Split('\n');
    foreach (var line in lines)
    {
        if (line.Trim().Length > 0 && line.Trim() != "InstallDate")
        {
            return line.Trim();
        }
    }
    return string.Empty;
}