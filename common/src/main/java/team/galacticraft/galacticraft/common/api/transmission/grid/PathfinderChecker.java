package team.galacticraft.galacticraft.common.api.transmission.grid;

import team.galacticraft.galacticraft.common.api.transmission.NetworkType;
import team.galacticraft.galacticraft.common.api.transmission.tile.INetworkConnection;
import team.galacticraft.galacticraft.common.api.transmission.tile.ITransmitter;
import team.galacticraft.galacticraft.common.api.vector.BlockVec3;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Check if a conductor connects with another.
 *
 * @author Calclavia
 */
public class PathfinderChecker extends Pathfinder
{
    public PathfinderChecker(final Level world, final INetworkConnection targetConnector, final NetworkType networkType, final INetworkConnection... ignoreConnector)
    {
        super(new IPathCallBack()
        {
            @Override
            public Set<BlockVec3> getConnectedNodes(Pathfinder finder, BlockVec3 currentNode)
            {
                Set<BlockVec3> neighbors = new HashSet<BlockVec3>();

                for (int i = 0; i < 6; i++)
                {
                    Direction direction = Direction.from3DDataValue(i);
                    BlockVec3 position = currentNode.clone().modifyPositionFromSide(direction);
                    BlockEntity connectedBlock = position.getTileEntity(world);

                    if (connectedBlock instanceof ITransmitter && !Arrays.asList(ignoreConnector).contains(connectedBlock))
                    {
                        if (((ITransmitter) connectedBlock).canConnect(direction.getOpposite(), networkType))
                        {
                            neighbors.add(position);
                        }
                    }
                }

                return neighbors;
            }

            @Override
            public boolean onSearch(Pathfinder finder, BlockVec3 node)
            {
                if (node.getTileEntity(world) == targetConnector)
                {
                    finder.results.add(node);
                    return true;
                }

                return false;
            }
        });
    }
}