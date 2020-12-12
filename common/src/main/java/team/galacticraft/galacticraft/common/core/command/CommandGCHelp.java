package team.galacticraft.galacticraft.common.core.command;
//
//import team.galacticraft.galacticraft.common.Constants;
//import team.galacticraft.galacticraft.common.core.util.EnumColor;
//import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
//import team.galacticraft.galacticraft.common.core.util.PlayerUtil;
//import net.minecraft.command.CommandBase;
//import net.minecraft.command.CommandException;
//import net.minecraft.command.ICommandSender;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.util.text.ITextComponent;
//
//public class CommandGCHelp extends CommandBase
//{
//    @Override
//    public String getUsage(ICommandSender var1)
//    {
//        return "/" + this.getName();
//    }
//
//    @Override
//    public int getRequiredPermissionLevel()
//    {
//        return 0;
//    }
//
//    @Override
//    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
//    {
//        return true;
//    }
//
//    @Override
//    public String getName()
//    {
//        return "gchelp";
//    }
//
//    @Override
//    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
//    {
//        ServerPlayerEntity playerBase = PlayerUtil.getPlayerBaseServerFromPlayerUsername(sender.getName(), true);
//        if (playerBase == null)
//        {
//            return;
//        }
//        playerBase.sendMessage(ITextComponent.Serializer.jsonToComponent("[{\"text\":\"" + I18n.get("gui.message.help1") + ": \",\"color\":\"white\"}," + "{\"text\":\" " + EnumColor.BRIGHT_GREEN + "wiki." + Constants.PREFIX + "com" + "\"," + "\"color\":\"green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":" + "{\"text\":\"" + I18n.get("gui.message.clicklink") + "\",\"color\":\"yellow\"}}," + "\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + "http://wiki." + Constants.PREFIX + "com/wiki" + "\"}}]"));
//    }
//}