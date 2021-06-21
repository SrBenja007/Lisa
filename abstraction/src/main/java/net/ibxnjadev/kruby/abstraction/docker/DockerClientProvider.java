package net.ibxnjadev.kruby.abstraction.docker;

import com.github.dockerjava.api.DockerClient;
import net.ibxnjadev.kruby.abstraction.util.ClientProvider;

/**
 * This class provides you with a docker client
 */

public interface DockerClientProvider extends ClientProvider<DockerClient> {

    /**
     * Establishes a connection with the docker that is on the machine running the cloud
     */

    void establishConnection();

}
