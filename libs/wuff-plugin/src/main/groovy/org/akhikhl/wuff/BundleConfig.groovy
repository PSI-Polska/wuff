package org.akhikhl.wuff

class BundleConfig {
    private String bundleName
    private List<String> importsToExclude

    private BundleConfig(Builder builder) {
        this.bundleName = builder.bundleName
        this.importsToExclude = builder.importsToExclude
    }

    String getBundleName() {
        return bundleName
    }

    List<String> getImportsToExclude() {
        return importsToExclude
    }

    static class Builder {
        private String bundleName
        private List<String> importsToExclude = []

        Builder(String bundleName) {
            if (bundleName == null) {
                throw new IllegalArgumentException('bundleName cannot be null')
            }
            this.bundleName = bundleName
        }

        Builder importsToExclude(List<String> importsToExclude) {
            if (importsToExclude == null) {
                throw new IllegalArgumentException('importsToExclude cannot be null')
            }
            this.importsToExclude.addAll(importsToExclude)
            return this
        }

        BundleConfig build() {
            return new BundleConfig(this)
        }
    }
}
