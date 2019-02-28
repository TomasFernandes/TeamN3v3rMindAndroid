using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Discord.Commands.Builders;
using Discord;
using Discord.Webhook;
using Discord.WebSocket;
using Discord.Commands;
using System.Net;
using System.Reflection;

namespace DiscordBot.Core
{
    class Discord
    {
        private DiscordSocketClient _client;
        private CommandService _command;
        public Discord(DiscordSocketClient client)
        {
            _client = client;

            _command = new CommandService();
             _command.AddModulesAsync(Assembly.GetEntryAssembly());

            _client.MessageReceived += CommandHandler;

        }
       
        private async Task CommandHandler(SocketMessage s)
        {
            var msg = s as SocketUserMessage;

             if(msg == null)
            {
                return;
            }
            var context = new SocketCommandContext(_client, msg);
            int argPos = 0;
            if(msg.HasCharPrefix('!', ref argPos) || msg.HasMentionPrefix(_client.CurrentUser, ref argPos))
            {
                var result = await _command.ExecuteAsync(context, argPos);
                if (!result.IsSuccess || result.Error != CommandError.UnknownCommand)
                {
                    await context.Channel.SendMessageAsync(result.ErrorReason);
                }
            }
        }
        //public void twich()
        //{
        //    var json = new webclient().downloadstring("https://api.twitch.tv/kraken/streams/tomas1207?client_id=8e6fsz2mnoy60vh1frz7ac12bg2rr4");
        //    var temp = jsonconvert.serializeobject(json);
        //    var _temp = jsonconvert.deserializeobject<dynamic>(json);
        //    if (_temp.tostring() != null)
        //    {
        //        if (_temp.stream == null)
        //        {

        //        }
        //    }
        //}

    }
}
