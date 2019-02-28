using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Discord;
using Discord.Webhook;
using Discord.WebSocket;
using Discord.Commands;
using DiscordBot.Core;

namespace DiscordBot
{
    class Program
    {
        DiscordBot.Core.Discord discord;
        static void Main(string[] args)
       => new Program().StartAsync().GetAwaiter().GetResult();
        public async Task StartAsync()
        {
            Taskauto taskauto = new Taskauto();
            DiscordSocketClient _discord = new DiscordSocketClient();
            await _discord.LoginAsync(TokenType.Bot, "NDQ5MjE5NDI0MjgxNjkwMTE1.DeoEog.pJ9rl8aOJh9X9Fv_3L-viULFFsU");
            _discord.Ready += Taskauto.startTimer;
            await _discord.StartAsync();

            Global.client = _discord;
            discord = new DiscordBot.Core.Discord(_discord);
            await Task.Delay(-1);

          
        }

        
    }
}
