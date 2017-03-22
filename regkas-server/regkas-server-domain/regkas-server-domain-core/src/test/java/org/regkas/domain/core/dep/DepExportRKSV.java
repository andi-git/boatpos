package org.regkas.domain.core.dep;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DepExportRKSV {

    @Expose
    @SerializedName("Belege-Gruppe")
    private List<BelegeGruppe> belegeGruppe;

    public List<BelegeGruppe> getBelegeGruppe() {
        return belegeGruppe;
    }

    public void setBelegeGruppe(List<BelegeGruppe> belegeGruppe) {
        this.belegeGruppe = belegeGruppe;
    }

    public static class BelegeGruppe {

        @Expose
        @SerializedName("Signaturzertifikat")
        private String signaturzertifikat;

        @Expose
        @SerializedName("Zertifizierungsstellen")
        private List<String> zertifizierungsstellen;

        @Expose
        @SerializedName("Belege-kompakt")
        private List<String> belegeKompakt;

        public String getSignaturzertifikat() {
            return signaturzertifikat;
        }

        public void setSignaturzertifikat(String signaturzertifikat) {
            this.signaturzertifikat = signaturzertifikat;
        }

        public List<String> getZertifizierungsstellen() {
            return zertifizierungsstellen;
        }

        public void setZertifizierungsstellen(List<String> zertifizierungsstellen) {
            this.zertifizierungsstellen = zertifizierungsstellen;
        }

        public List<String> getBelegeKompakt() {
            return belegeKompakt;
        }

        public void setBelegeKompakt(List<String> belegeKompakt) {
            this.belegeKompakt = belegeKompakt;
        }
    }
}
