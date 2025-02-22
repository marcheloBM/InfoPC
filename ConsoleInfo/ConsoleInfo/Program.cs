using System.Diagnostics;
using System.Globalization;

string marca = EjecutarComandoWMIC("/c wmic computersystem get name");
marca = ExtraerValorLimpio(marca);
Console.WriteLine("Marca: " + marca);

string modelo = EjecutarComandoWMIC("/c wmic csproduct get name");
modelo = ExtraerValorLimpio(modelo);
Console.WriteLine("Modelo: " + modelo);

string numSerie = EjecutarComandoWMIC("/c wmic bios get serialnumber");
numSerie = ExtraerValorLimpio(numSerie);
Console.WriteLine("Número de Serie: " + numSerie);

string uuid = EjecutarComandoWMIC("/c wmic path win32_computersystemproduct get uuid");
uuid = ExtraerValorLimpio(uuid);
Console.WriteLine("UUID: " + uuid);

string sistemaOperativo = EjecutarComandoWMIC("/c wmic os get Caption");
sistemaOperativo = ExtraerValorLimpio(sistemaOperativo);
Console.WriteLine("Sistema Operativo: " + sistemaOperativo);

string arqui = EjecutarComandoWMIC("/c wmic os get osarchitecture");
arqui = ExtraerValorLimpio(arqui);
Console.WriteLine("Arquitectura del SO: " + arqui);

string fechaInsta = EjecutarComandoWMIC("/c wmic os get installdate");
string installDate = ExtractInstallDate(fechaInsta);
DateTime fechaConvertida = DateTime.ParseExact(installDate.Substring(0, 14), "yyyyMMddHHmmss", CultureInfo.InvariantCulture);
Console.WriteLine("Fecha de Instalación: " + fechaConvertida.ToString("yyyy-MM-dd HH:mm:ss"));

Console.WriteLine("Presiona Enter para salir...");
Console.ReadLine();

static string EjecutarComandoWMIC(string comando)
{
    Process process = new Process();
    process.StartInfo.FileName = "cmd.exe";
    process.StartInfo.Arguments = comando;
    process.StartInfo.RedirectStandardOutput = true;
    process.StartInfo.UseShellExecute = false;
    process.StartInfo.CreateNoWindow = true;

    process.Start();
    process.WaitForExit();

    string output = process.StandardOutput.ReadToEnd();
    return output;
}

static string ExtraerValorLimpio(string wmicOutput)
{
    string[] lines = wmicOutput.Split('\n');
    foreach (var line in lines)
    {
        if (!string.IsNullOrWhiteSpace(line) && !line.Trim().Contains("Name") && !line.Trim().Contains("SerialNumber") && !line.Trim().Contains("UUID") && !line.Trim().Contains("InstallDate") && !line.Trim().Contains("OSArchitecture") && !line.Trim().Contains("Caption"))
        {
            return line.Trim();
        }
    }
    return string.Empty;
}

static string ExtractInstallDate(string wmicOutput)
{
    string[] lines = wmicOutput.Split('\n');
    foreach (var line in lines)
    {
        if (!string.IsNullOrWhiteSpace(line) && line.Trim() != "InstallDate")
        {
            return line.Trim();
        }
    }
    return string.Empty;
}
