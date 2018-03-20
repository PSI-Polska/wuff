package org.akhikhl.wuff

class BundleConfigsExtension {
    private List<BundleConfig> bundleConfigs = []

    void addBundleConfig(BundleConfig bundleConfig) {
        bundleConfigs.add(bundleConfig)
    }

    void addBundleConfig(String bundleName, List<String> importsToExclude) {
        addBundleConfig(new BundleConfig.Builder(bundleName).importsToExclude(importsToExclude).build())
    }

    boolean containsBundle(String bundleName) {
        return bundleConfigs.any { it.bundleName == bundleName }
    }

    boolean containsBundle(File bundleFile) {
        return containsBundle(PluginUtils.getPluginName(bundleFile.name))
    }

    List<String> getImportsToExclude(String bundleName) {
        if (containsBundle(bundleName)) {
            return bundleConfigs.findAll { it.bundleName == bundleName }
                    .collect { it.importsToExclude }
                    .flatten()
        } else {
            return []
        }
    }

    private Map excludeImports(Map baseImports, List<String> importsToExclude) {
        Map newImports = baseImports
        importsToExclude.each { importToExclude ->
            newImports = newImports.findAll { !it.key.startsWith(importToExclude) }
        }
        return newImports
    }

    Map filterBundleImports(String bundleName, Map baseImports) {
        return excludeImports(baseImports, getImportsToExclude(bundleName))
    }

    Map filterBundleImports(File bundleFile, Map baseImports) {
        return filterBundleImports(PluginUtils.getPluginName(bundleFile.name), baseImports)
    }

    String filterBundleImports(String bundleName, String baseImports) {
        Map filteredImports = filterBundleImports(bundleName, ManifestUtils.parsePackages(baseImports))
        return ManifestUtils.packagesToString(filteredImports)
    }
}
