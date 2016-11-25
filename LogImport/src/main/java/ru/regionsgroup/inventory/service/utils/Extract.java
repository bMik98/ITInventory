package ru.regionsgroup.inventory.service.utils;

import ru.regionsgroup.inventory.model.InstalledSoftware;
import ru.regionsgroup.inventory.model.PrinterConnection;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Mbedritskiy
 */
public class Extract {
    public static Set<String> extractComputerIdsFromPrinterConnection(final List<PrinterConnection> entities) {
        Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (PrinterConnection entity: entities ) {
            set.add(entity.getComputerId());
        }
        return set;
    }

    public static Set<String> extractComputerIdsFromInstalledSoftware(final List<InstalledSoftware> entities) {
        Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (InstalledSoftware entity: entities ) {
            set.add(entity.getComputerId());
        }
        //set.stream().collect(Collectors.toList())
        return set;
    }
}
