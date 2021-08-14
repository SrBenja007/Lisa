package net.ibxnjadev.kruby.core.setup;

import net.ibxnjadev.kruby.core.cloud.CloudConfiguration;
import net.ibxnjadev.kruby.core.util.Executor;
import net.ibxnjadev.kruby.core.util.InputExecutor;
import net.ibxnjadev.kruby.core.util.IpProvider;
import net.ibxnjadev.kruby.core.util.ObjectFileStorageHelper;
import net.ibxnjadev.kruby.core.util.UtilId;

public class SetupCloudConfiguration implements SetupHandler {

    private final InputExecutor inputExecutor;

    private final Executor errorInput = new ErrorInput();
    private final CloudConfiguration configuration;

    public SetupCloudConfiguration(CloudConfiguration configuration, InputExecutor inputExecutor) {
        this.configuration = configuration;
        this.inputExecutor = inputExecutor;
    }

    @Override
    public void setup() {
        System.out.println("------------------------------");
        System.out.println(">> We are setting up kruby");
        System.out.println(">> Please follow the instructions");
        System.out.println("------------------------------");

        System.out.println(">> Please write a name for your cloud");

        String idRandom = UtilId.randomId();

        configuration.setId(idRandom);

        inputExecutor
                .execute(String.class, (s) -> {
                    configuration.setName(s + "_" + idRandom);
                }, errorInput, "Enter Cloud Name: ");

        System.out.println(">> Now please enter the memory in GB that you want to assign to the cloud");

        inputExecutor
                .execute(Integer.class, configuration::setMemory, errorInput, "Enter Ram: ");

        configuration
                .setAddress(IpProvider.provideIp());

        System.out.println("------------------------------");
        System.out.println("Configured cloud....");
        System.out.println("Id " + configuration.getId());
        System.out.println("Name " + configuration.getName());
        System.out.println("Memory " + configuration.getMemory());
        System.out.println("IP " + configuration.getAddress());
        System.out.println("------------------------------");

        ObjectFileStorageHelper.save(configuration);
    }

    private static class ErrorInput implements Executor {
        @Override
        public void execute() {
            System.out.println(">> You have entered a data incorrectly, please enter the requested information again");
        }
    }

}
