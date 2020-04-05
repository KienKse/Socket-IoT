package Socket.IoT.model;

public class Container {

	private Integer idContainer;
    private Integer[] tipoContainer = {0, 0, 0, 0};
    private boolean caminhao = false;
    /**
     0 - PAPEL
     1 - VIDRO
     2 - METAL
     4 - PLASTICO
     */

    public Container(Integer idContainer) {
        this.idContainer = idContainer;
    }

    public Integer getIdContainer() {
        return idContainer;
    }

    public Integer[] getTipoContainer() {
        return tipoContainer;
    }

    public void setTipoContainer(Integer[] tipoContainer) {
        this.tipoContainer = tipoContainer;
    }

    public boolean isCaminhao() {
        return caminhao;
    }

    public void setCaminhao(boolean caminhao) {
        this.caminhao = caminhao;
    }
}
