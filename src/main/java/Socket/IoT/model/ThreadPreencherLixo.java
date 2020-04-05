package Socket.IoT.model;

public class ThreadPreencherLixo implements Runnable {

    private Container container;
    private Integer contadorContainer;


    ThreadPreencherLixo(Container container, boolean caminhao) {
        this.container = container;
        this.container.setCaminhao(caminhao);
        this.contadorContainer = 0;
    }

    @Override
    public void run() {
        if(!this.container.isCaminhao()) {
            while (contadorContainer < 100) {
                contadorContainer += (int) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + " - " + contadorContainer);
                try {
                    Thread.sleep((int) (Math.random() * 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i <= this.container.getTipoContainer().length - 1; i++) {
                if (this.container.getTipoContainer()[i] == 0) {
                    this.container.getTipoContainer()[i] = 1;
                    //TODO: MUDAR FORMA DE OBTER TIPO DE LIXO
                    String msg = "CHEIO " + conteverTipo(i) + " " + container.getIdContainer();
                    new Thread(new ThreadCliente(msg)).start();
                    System.out.println(msg);
                    break;
                }
            }
        } else {
            System.out.println("Esvaziando Container");
            this.container.setTipoContainer(new Integer[]{0, 0, 0, 0});
            this.container.setCaminhao(false);
        }
        imprimirContainers();
    }

    private String conteverTipo(int i) {
        String resp = "";
        switch (i) {
            case 0:
                resp = "PAPEL";
                break;
            case 1:
                resp = "VIDRO";
                break;
            case 2:
                resp = "METAL";
                break;
            case 3:
                resp = "PLASTICO";
                break;
        }
        return resp;
    }

    private void imprimirContainers() {
        System.out.println("_______________");
        System.out.println(this.container.getTipoContainer()[0] + " " + this.container.getTipoContainer()[1] + " "
                + this.container.getTipoContainer()[2] + " " + this.container.getTipoContainer()[3]);
        System.out.println("_______________");
    }
}
