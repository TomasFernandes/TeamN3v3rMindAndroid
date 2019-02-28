using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Discord.Commands;

namespace DiscordBot.Core
{
   public class Commands:ModuleBase<SocketCommandContext>
    {
        [Command("test")]
        public async Task Star()
        {
            twich();
        }
        [Command("Ping")]
        public async Task ping()
        {
            Context.Channel.SendMessageAsync("Pong");
        }
        //TODO: Add Falata a parte automatica e a parte dos outros menbros da team, a auto vai ser com uma var que ve o ultimo status da live para nao repitir avisos
        //Um for infinito para ficar sempre a ver as live, para o futoro o comando para iniciar o bloq de palavras
        //TODO:Add
        public void twich()
        {
            var json = new WebClient().DownloadString("https://api.twitch.tv/kraken/streams/Tomas1207?client_id=8e6fsz2mnoy60vh1frz7ac12bg2rr4");
          //  var temp = JsonConvert.SerializeObject(json);
            var _temp = JsonConvert.DeserializeObject<dynamic>(json);
            if (_temp.ToString() != null)
            {
                if (_temp.stream != null)
                {
                    Context.Channel.SendMessageAsync("@everyone O Tomas1207 esta em live");
                }
                else
                {
                    Context.Channel.SendMessageAsync("@everyone O Tomas1207 nao esta em live");

                }
            }
        }
    }
}
