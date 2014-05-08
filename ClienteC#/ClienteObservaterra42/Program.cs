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
                case '1': menuCountry(wb);
                    break;
                case '2': menuObservations(wb);
                    break;
                case '3': menuIndicators(wb);
                    break;
                default:
                    Console.Write("Opcion incorrecta");
                    break;

            }
            Console.ReadKey();
        }

        public static void menuCountry(ClienteWeb cw)
        {
            Console.Write("1º) Mostrar todos" + "\n" + 
                "2º) Mostrar pais " + "\n");
            Console.Write("Seleccione una opción: ");
            switch (Console.Read())
            {
                case '1': cw.showCountry();
                    break;
                case '2':
                    String pais = Console.ReadLine();
                    cw.showCountry(pais);
                    break;
                default:
                    Console.WriteLine("Opcion erronea");
                    break;
            }
            Console.ReadKey();
        }

        public static void menuObservations(ClienteWeb cw)
        {
            Console.Write("1º) Mostrar todos" + "\n" +
                "2º) Mostrar observacion " + "\n");
            Console.Write("Seleccione una opción: ");
            switch (Console.Read())
            {
                case '1': cw.showObservation();
                    break;
                case '2':
                    String observacion = Console.ReadLine();
                    cw.showObservation(observacion);
                    break;
                default:
                    Console.WriteLine("Opcion erronea");
                    break;

            }
            Console.ReadKey();
        }

        public static void menuIndicators(ClienteWeb cw)
        {
            Console.Write("1º) Mostrar todos" + "\n" +
                "2º) Mostrar indicador " + "\n");
            Console.Write("Seleccione una opción: ");
            switch (Console.Read())
            {
                case '1': cw.showIndicator();
                    break;
                case '2':
                    String indicador = Console.ReadLine();
                    cw.showIndicator(indicador);
                    break;
                default:
                    Console.WriteLine("Opcion erronea");
                    break;
            }
            Console.ReadKey();
        }
    }
}
