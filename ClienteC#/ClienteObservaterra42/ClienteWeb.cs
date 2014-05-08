using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace ClienteAPI
{
    class ClienteWeb
    {
        public WebClient wc;

        public ClienteWeb()
        {
            wc = new WebClient();
        }

        public String showCountry()
        {
            return wc.DownloadString("http://localhost:9000/api/country");
        }

        public String showCountry(String pais)
        {
            return wc.DownloadString("http://localhost:9000/api/country/" + pais);
        }

        public String showObservation()
        {
            return wc.DownloadString("http://localhost:9000/api/observation");
        }

        public String showObservation(String observacion)
        {
            return wc.DownloadString("http://localhost:9000/api/observation/" + observacion);
        }

        public String showIndicator()
        {
            return wc.DownloadString("http://localhost:9000/api/indication");
        }

        public String showIndicator(String indicacion)
        {
            return wc.DownloadString("http://localhost:9000/api/indication/" + indicacion);
        }

    }
}
