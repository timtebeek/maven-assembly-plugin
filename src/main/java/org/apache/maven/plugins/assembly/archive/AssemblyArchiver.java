/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.plugins.assembly.archive;

import java.io.File;
import java.nio.file.attribute.FileTime;

import org.apache.maven.plugins.assembly.AssemblerConfigurationSource;
import org.apache.maven.plugins.assembly.InvalidAssemblerConfigurationException;
import org.apache.maven.plugins.assembly.format.AssemblyFormattingException;
import org.apache.maven.plugins.assembly.model.Assembly;

/**
 * Creates an archive
 *
 *
 */
public interface AssemblyArchiver {
    /**
     * Create the assembly archive. Generally:
     * <ol>
     * <li>Setup any directory structures for temporary files</li>
     * <li>Calculate the output directory/file for the assembly</li>
     * <li>Setup any handler components for special descriptor files we may encounter</li>
     * <li>Lookup and configure the {@link org.codehaus.plexus.archiver.Archiver} to be used</li>
     * <li>Determine what, if any, dependency resolution will be required, and resolve any dependency-version conflicts
     * up front to produce a managed-version map for the whole assembly process.</li>
     * <li>Iterate through the available {@link org.apache.maven.plugins.assembly.archive.phase.AssemblyArchiverPhase}
     * instances, executing each to handle a different
     * top-level section of the assembly descriptor, if that section is present.</li>
     * </ol>
     *
     * @param assembly              The {@link Assembly}
     * @param fullName              The full name.
     * @param format                The format.
     * @param configSource          The {@link org.apache.maven.plugins.assembly.AssemblerConfigurationSource}
     * @param recompressZippedFiles recompress zipped files.
     * @param mergeManifestMode     How to handle already existing Manifest files (skip, merge, mergewithoutmain)
     * @param sourceDateEpoch       Timestamp for reproducible archive entries
     * @return The resulting archive file.
     * @throws ArchiveCreationException                                                 when creation fails
     * @throws org.apache.maven.plugins.assembly.format.AssemblyFormattingException     when formatting fails
     * @throws org.apache.maven.plugins.assembly.InvalidAssemblerConfigurationException when the configuration is bad
     */
    File createArchive(
            Assembly assembly,
            String fullName,
            String format,
            AssemblerConfigurationSource configSource,
            boolean recompressZippedFiles,
            String mergeManifestMode,
            FileTime sourceDateEpoch)
            throws ArchiveCreationException, AssemblyFormattingException, InvalidAssemblerConfigurationException;
}
