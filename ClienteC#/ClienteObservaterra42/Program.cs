using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteAPI
{
    class Program
    {
        static void Main(string[] args)
        {
            ClienteWeb wb = new ClienteWeb();
            Console.Write( "1º) Paises" + "\n" + "2º) Observaciones"+ "\n" + "3º) Indicadores" + "\n");
            Console.Write("Seleccione una opción: ");
            switch (Console.Read())
            {
                case '1': Console.Write("Sub menu paises");
                // Continuar lógica y extraer métodos //
                    break;
            }
            Console.ReadKey();
        }

        public static void menuCountry()
        {
        }
    }
}
