using Discord.WebSocket;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Timers;        

namespace DiscordBot.Core
{
  internal class Taskauto
    {
        private static SocketTextChannel channel;
        private static Timer Looping;
        private static string[] arruser = new string[] {"Tomas1207", "x01xico", "xtr34mPT", "06murat19", "adaocomtil_a", "devil_84" };
        private static string[] arrLink = new string[] { "https://www.twitch.tv/tomas1207", "https://www.twitch.tv/x01xico", "https://www.twitch.tv/xtr34mpt", "https://www.twitch.tv/06murat19","https://www.twitch.tv/adaocomtil_a", "https://www.twitch.tv/devil_84" };
        private static bool[] Islive = new bool[] { true,true,true,true,true,true,true,true,true,true };
        internal async static Task startTimer()
        {
          // channel = Global.client.GetGuild(449641457998102528).GetTextChannel(449641457998102530); Discord de testes
            channel = Global.client.GetGuild(428808558839070720).GetTextChannel(428808558839070722);
            Looping = new Timer()
            {
                Interval = 10000,
                AutoReset = true,
                Enabled = true
            };
            Looping.Elapsed += OnTimerticked;

        }
      
        private async static void  OnTimerticked(object sender, ElapsedEventArgs e)
        {
            
            for (int i = 0; i <= arruser.Length -1 ;i++) {
                var json = new WebClient().DownloadString("https://api.twitch.tv/kraken/streams/" + arruser[i]  + "?client_id=8e6fsz2mnoy60vh1frz7ac12bg2rr4");
                var temp = JsonConvert.SerializeObject(json);
                var _temp = JsonConvert.DeserializeObject<dynamic>(json);
              
                if (_temp != null)
                {
                   
                        if (_temp.stream != null)
                        {
                            if (Islive[i] == true)
                            {
                            await channel.SendMessageAsync("@everyone Is now on stream " + arruser[i]);
                            await channel.SendMessageAsync("Link: " + arrLink[i]);
                            Islive[i] = false;
                            TwichtBot.path path = new TwichtBot.path();
                           Process.Start(path.Path() + "/TwichtBot.exe");




                        }

                    }
                        else
                        {
                            //await channel.SendMessageAsync("Nao esta " + arruser[i]);
                        }
                    }
                    
                    if(_temp.stream == null)
                    {
                        Islive[i] = true;
                    }
                }
            }
            
        }
    }

namespace TwichtBot
{
    public class Program
    {
    }
}