using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Collections.Specialized;
using System.IO;

namespace ClienteAPI
{
    class ClienteWeb
    {
        public WebClient wc;

        public ClienteWeb()
        {
            wc = new WebClient();
        }

        public bool Conexion()
        {
            try
            {
                var Req = (System.Net.HttpWebRequest)System.Net.WebRequest.Create("https://www.google.es");
                var res = (System.Net.HttpWebResponse)Req.GetResponse();

                Req.Abort();

                if (res.StatusCode == System.Net.HttpStatusCode.OK)
                    return true;
                else
                    return false;
            }
            catch
            {
                return false;
            }
        }

        public void showCountry()
        {
            String s = wc.DownloadString("http://localhost:9000/api/country");
            parseString(s);
        }

        public void showCountry(String pais)
        {
            try
            {
                String s = wc.DownloadString("http://localhost:9000/api/country/" + pais);
                parseString(s);
            }
            catch (Exception e)
            {
                Console.WriteLine("El codigo introducido no es correcto");
            }

        }

        public void showObservation()
        {
            String s = wc.DownloadString("http://localhost:9000/api/observation");
            parseString(s);
        }

        public void showObservation(String observacion)
        {
            try
            {
                String s = wc.DownloadString("http://localhost:9000/api/observation/" + observacion);
                parseString(s);
            }
            catch (Exception e)
            {
                Console.WriteLine("El codigo introducido no es correcto");
            }
        }

        public void showIndicator()
        {
            String s = wc.DownloadString("http://localhost:9000/api/indication");
            parseString(s);
        }

        public void showIndicator(String indicacion)
        {
            try
            {
                String s = wc.DownloadString("http://localhost:9000/api/indication/" + indicacion);
                parseString(s);
            }
            catch (Exception e)
            {
                Console.WriteLine("El codigo introducido no es correcto");
            }
        }

        public void parseString(String s)
        {
            foreach (var x in s)
            {
                Console.Write(x);
                if (x.Equals('}'))
                {
                    Console.WriteLine();
                }
            }
        }

        
        public Boolean login(String username, String password)
        {
            string formUrl = "localhost:9000/login?controllers.Application.authenticate()"; // NOTE: This is the URL the form POSTs to, not the URL of the form (you can find this in the "action" attribute of the HTML's form tag
            string formParams = string.Format("username={0}&password={1}", username,  password);
            string cookieHeader;
            WebRequest req = WebRequest.Create(formUrl);
            req.ContentType = "application/x-www-form-urlencoded";
            req.Method = "POST";
            byte[] bytes = Encoding.ASCII.GetBytes(formParams);
            req.ContentLength = bytes.Length;
            using (Stream os = req.GetRequestStream())
            {
                os.Write(bytes, 0, bytes.Length);
            }
            WebResponse resp = req.GetResponse();
            cookieHeader = resp.Headers["Set-cookie"];
            return true;
        }

    }
}
