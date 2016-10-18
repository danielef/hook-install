package mx.interware.maven.plugin;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.repository.RepositoryManager;

import java.io.File;
import java.util.List;

@Mojo( name = "hook", defaultPhase = LifecyclePhase.INSTALL )
public class HookMojo extends AbstractMojo {

    @Parameter(required = true)
    private String myparam;

    @Parameter(defaultValue = "${reactorProjects}", required = true, readonly = true)
    private List<MavenProject> reactorProjects;

    @Component
    protected RepositoryManager repositoryManager;

    @Parameter( property = "localRepository", required = true, readonly = true )
    protected ArtifactRepository localRepository;

    @Parameter( defaultValue = "${session}", required = true, readonly = true )
    protected MavenSession session;

    public void execute() throws MojoExecutionException, MojoFailureException {

        System.out.println(" >> My Param: " + myparam);

        for (MavenProject project : reactorProjects) {
            Artifact artifact = project.getArtifact();
            System.out.println(" >> Deploy this file:" + getLocalRepoFile(artifact));
        }

    }

    /**
     * Gets the path of the specified artifact within the local repository. Note that the returned path need not exist
     * (yet).
     *
     * @param artifact The artifact whose local repo path should be determined, must not be <code>null</code>.
     * @return The absolute path to the artifact when installed, never <code>null</code>.
     */
    protected File getLocalRepoFile( Artifact artifact ) {
        String path = repositoryManager.getPathForLocalArtifact( session.getProjectBuildingRequest(), artifact );
        return new File( localRepository.getBasedir(), path );
    }

}
