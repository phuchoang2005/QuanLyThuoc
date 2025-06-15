package org.cacanhdaden.quanlythuoc.model;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineModel {
    private String TenThuoc, DonVi, GhiChu, TanSuatChung, TanSuatCuThe;
    private boolean UuTienThongBao, TuDongXacNhan;
    private int KhoangThongBao, SLHienCo, SLNhacNho;
    private List<List<Object>> DSMocThoiGian;
    private List<Object> DSTanSuatCuThe;
}
