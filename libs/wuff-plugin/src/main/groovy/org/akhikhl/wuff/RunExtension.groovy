/*
 * wuff
 *
 * Copyright 2014-2015 Andrey Hihlovskiy and contributors.
 *
 * See the file "LICENSE" for copying and usage permission.
 */
package org.akhikhl.wuff

/**
 *
 * @author akhikhl
 */
class RunExtension {

  def args = []
  def jvmArgs = []
  String language
  def autostartedBundles = []
  Map<String, Integer> bundleStartLevelMap = [:]

  def arg(String newValue) {
    args.add newValue
  }

  def jvmArg(String newValue) {
    jvmArgs.add newValue
  }

  def jvmArg(Object[] newValue) {
      jvmArgs.addAll newValue
  }

  def args(Object[] newValue) {
    args.addAll newValue
  }

  def language(String newValue) {
    language = newValue
  }

  void autostartedBundle(String newBundle) {
    autostartedBundles.add newBundle
  }

  void autostartedBundle(String[] newBundles) {
    autostartedBundles.addAll newBundles
  }

  void putBundleStartLevelEntry(String newBundle, Integer newStartLevel) {
    bundleStartLevelMap.put newBundle, newStartLevel
  }

  void putAllBundleStartLevelEntries(Map<String, Integer> newBundleStartLevelMap) {
    bundleStartLevelMap.putAll newBundleStartLevelMap
  }
}
