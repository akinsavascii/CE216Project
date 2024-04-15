package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final JavaxLibraryAccessors laccForJavaxLibraryAccessors = new JavaxLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>javax</b>
     */
    public JavaxLibraryAccessors getJavax() {
        return laccForJavaxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google</b>
         */
        public ComGoogleLibraryAccessors getGoogle() {
            return laccForComGoogleLibraryAccessors;
        }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeLibraryAccessors laccForComGoogleCodeLibraryAccessors = new ComGoogleCodeLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code</b>
         */
        public ComGoogleCodeLibraryAccessors getCode() {
            return laccForComGoogleCodeLibraryAccessors;
        }

    }

    public static class ComGoogleCodeLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeGsonLibraryAccessors laccForComGoogleCodeGsonLibraryAccessors = new ComGoogleCodeGsonLibraryAccessors(owner);

        public ComGoogleCodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code.gson</b>
         */
        public ComGoogleCodeGsonLibraryAccessors getGson() {
            return laccForComGoogleCodeGsonLibraryAccessors;
        }

    }

    public static class ComGoogleCodeGsonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleCodeGsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gson</b> with <b>com.google.code.gson:gson</b> coordinates and
         * with version reference <b>com.google.code.gson.gson</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("com.google.code.gson.gson");
        }

    }

    public static class JavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxJsonLibraryAccessors laccForJavaxJsonLibraryAccessors = new JavaxJsonLibraryAccessors(owner);

        public JavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.json</b>
         */
        public JavaxJsonLibraryAccessors getJson() {
            return laccForJavaxJsonLibraryAccessors;
        }

    }

    public static class JavaxJsonLibraryAccessors extends SubDependencyFactory {
        private final JavaxJsonJavaxLibraryAccessors laccForJavaxJsonJavaxLibraryAccessors = new JavaxJsonJavaxLibraryAccessors(owner);

        public JavaxJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.json.javax</b>
         */
        public JavaxJsonJavaxLibraryAccessors getJavax() {
            return laccForJavaxJsonJavaxLibraryAccessors;
        }

    }

    public static class JavaxJsonJavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxJsonJavaxJsonLibraryAccessors laccForJavaxJsonJavaxJsonLibraryAccessors = new JavaxJsonJavaxJsonLibraryAccessors(owner);

        public JavaxJsonJavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>javax.json.javax.json</b>
         */
        public JavaxJsonJavaxJsonLibraryAccessors getJson() {
            return laccForJavaxJsonJavaxJsonLibraryAccessors;
        }

    }

    public static class JavaxJsonJavaxJsonLibraryAccessors extends SubDependencyFactory {

        public JavaxJsonJavaxJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>javax.json:javax.json-api</b> coordinates and
         * with version reference <b>javax.json.javax.json.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("javax.json.javax.json.api");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishLibraryAccessors laccForOrgGlassfishLibraryAccessors = new OrgGlassfishLibraryAccessors(owner);
        private final OrgOpenjfxLibraryAccessors laccForOrgOpenjfxLibraryAccessors = new OrgOpenjfxLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish</b>
         */
        public OrgGlassfishLibraryAccessors getGlassfish() {
            return laccForOrgGlassfishLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.openjfx</b>
         */
        public OrgOpenjfxLibraryAccessors getOpenjfx() {
            return laccForOrgOpenjfxLibraryAccessors;
        }

    }

    public static class OrgGlassfishLibraryAccessors extends SubDependencyFactory {
        private final OrgGlassfishJavaxLibraryAccessors laccForOrgGlassfishJavaxLibraryAccessors = new OrgGlassfishJavaxLibraryAccessors(owner);

        public OrgGlassfishLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.glassfish.javax</b>
         */
        public OrgGlassfishJavaxLibraryAccessors getJavax() {
            return laccForOrgGlassfishJavaxLibraryAccessors;
        }

    }

    public static class OrgGlassfishJavaxLibraryAccessors extends SubDependencyFactory {

        public OrgGlassfishJavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>json</b> with <b>org.glassfish:javax.json</b> coordinates and
         * with version reference <b>org.glassfish.javax.json</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJson() {
            return create("org.glassfish.javax.json");
        }

    }

    public static class OrgOpenjfxLibraryAccessors extends SubDependencyFactory {
        private final OrgOpenjfxJavafxLibraryAccessors laccForOrgOpenjfxJavafxLibraryAccessors = new OrgOpenjfxJavafxLibraryAccessors(owner);

        public OrgOpenjfxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.openjfx.javafx</b>
         */
        public OrgOpenjfxJavafxLibraryAccessors getJavafx() {
            return laccForOrgOpenjfxJavafxLibraryAccessors;
        }

    }

    public static class OrgOpenjfxJavafxLibraryAccessors extends SubDependencyFactory {

        public OrgOpenjfxJavafxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>base</b> with <b>org.openjfx:javafx-base</b> coordinates and
         * with version reference <b>org.openjfx.javafx.base</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBase() {
            return create("org.openjfx.javafx.base");
        }

        /**
         * Dependency provider for <b>controls</b> with <b>org.openjfx:javafx-controls</b> coordinates and
         * with version reference <b>org.openjfx.javafx.controls</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getControls() {
            return create("org.openjfx.javafx.controls");
        }

        /**
         * Dependency provider for <b>graphics</b> with <b>org.openjfx:javafx-graphics</b> coordinates and
         * with version reference <b>org.openjfx.javafx.graphics</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGraphics() {
            return create("org.openjfx.javafx.graphics");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final JavaxVersionAccessors vaccForJavaxVersionAccessors = new JavaxVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.javax</b>
         */
        public JavaxVersionAccessors getJavax() {
            return vaccForJavaxVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGoogleVersionAccessors vaccForComGoogleVersionAccessors = new ComGoogleVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google</b>
         */
        public ComGoogleVersionAccessors getGoogle() {
            return vaccForComGoogleVersionAccessors;
        }

    }

    public static class ComGoogleVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeVersionAccessors vaccForComGoogleCodeVersionAccessors = new ComGoogleCodeVersionAccessors(providers, config);
        public ComGoogleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code</b>
         */
        public ComGoogleCodeVersionAccessors getCode() {
            return vaccForComGoogleCodeVersionAccessors;
        }

    }

    public static class ComGoogleCodeVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeGsonVersionAccessors vaccForComGoogleCodeGsonVersionAccessors = new ComGoogleCodeGsonVersionAccessors(providers, config);
        public ComGoogleCodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code.gson</b>
         */
        public ComGoogleCodeGsonVersionAccessors getGson() {
            return vaccForComGoogleCodeGsonVersionAccessors;
        }

    }

    public static class ComGoogleCodeGsonVersionAccessors extends VersionFactory  {

        public ComGoogleCodeGsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.google.code.gson.gson</b> with value <b>2.8.9</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGson() { return getVersion("com.google.code.gson.gson"); }

    }

    public static class JavaxVersionAccessors extends VersionFactory  {

        private final JavaxJsonVersionAccessors vaccForJavaxJsonVersionAccessors = new JavaxJsonVersionAccessors(providers, config);
        public JavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.json</b>
         */
        public JavaxJsonVersionAccessors getJson() {
            return vaccForJavaxJsonVersionAccessors;
        }

    }

    public static class JavaxJsonVersionAccessors extends VersionFactory  {

        private final JavaxJsonJavaxVersionAccessors vaccForJavaxJsonJavaxVersionAccessors = new JavaxJsonJavaxVersionAccessors(providers, config);
        public JavaxJsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.json.javax</b>
         */
        public JavaxJsonJavaxVersionAccessors getJavax() {
            return vaccForJavaxJsonJavaxVersionAccessors;
        }

    }

    public static class JavaxJsonJavaxVersionAccessors extends VersionFactory  {

        private final JavaxJsonJavaxJsonVersionAccessors vaccForJavaxJsonJavaxJsonVersionAccessors = new JavaxJsonJavaxJsonVersionAccessors(providers, config);
        public JavaxJsonJavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.javax.json.javax.json</b>
         */
        public JavaxJsonJavaxJsonVersionAccessors getJson() {
            return vaccForJavaxJsonJavaxJsonVersionAccessors;
        }

    }

    public static class JavaxJsonJavaxJsonVersionAccessors extends VersionFactory  {

        public JavaxJsonJavaxJsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>javax.json.javax.json.api</b> with value <b>1.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("javax.json.javax.json.api"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgGlassfishVersionAccessors vaccForOrgGlassfishVersionAccessors = new OrgGlassfishVersionAccessors(providers, config);
        private final OrgOpenjfxVersionAccessors vaccForOrgOpenjfxVersionAccessors = new OrgOpenjfxVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish</b>
         */
        public OrgGlassfishVersionAccessors getGlassfish() {
            return vaccForOrgGlassfishVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.openjfx</b>
         */
        public OrgOpenjfxVersionAccessors getOpenjfx() {
            return vaccForOrgOpenjfxVersionAccessors;
        }

    }

    public static class OrgGlassfishVersionAccessors extends VersionFactory  {

        private final OrgGlassfishJavaxVersionAccessors vaccForOrgGlassfishJavaxVersionAccessors = new OrgGlassfishJavaxVersionAccessors(providers, config);
        public OrgGlassfishVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.glassfish.javax</b>
         */
        public OrgGlassfishJavaxVersionAccessors getJavax() {
            return vaccForOrgGlassfishJavaxVersionAccessors;
        }

    }

    public static class OrgGlassfishJavaxVersionAccessors extends VersionFactory  {

        public OrgGlassfishJavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.glassfish.javax.json</b> with value <b>1.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJson() { return getVersion("org.glassfish.javax.json"); }

    }

    public static class OrgOpenjfxVersionAccessors extends VersionFactory  {

        private final OrgOpenjfxJavafxVersionAccessors vaccForOrgOpenjfxJavafxVersionAccessors = new OrgOpenjfxJavafxVersionAccessors(providers, config);
        public OrgOpenjfxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.openjfx.javafx</b>
         */
        public OrgOpenjfxJavafxVersionAccessors getJavafx() {
            return vaccForOrgOpenjfxJavafxVersionAccessors;
        }

    }

    public static class OrgOpenjfxJavafxVersionAccessors extends VersionFactory  {

        public OrgOpenjfxJavafxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.openjfx.javafx.base</b> with value <b>21</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBase() { return getVersion("org.openjfx.javafx.base"); }

        /**
         * Version alias <b>org.openjfx.javafx.controls</b> with value <b>21.0.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getControls() { return getVersion("org.openjfx.javafx.controls"); }

        /**
         * Version alias <b>org.openjfx.javafx.graphics</b> with value <b>21.0.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGraphics() { return getVersion("org.openjfx.javafx.graphics"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
