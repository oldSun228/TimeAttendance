package com.dby.njxinch.action.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2018/3/7.
 */
public class TestGitController {
    //aaa
    private Git git = null;

    public void CommitCode(String proName, String proPath) throws IOException, NoFilepatternException, GitAPIException {
        try {
            Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File(proPath + "\\.git")).build();
            git = new Git(existingRepo);
            //true if no differences exist between the working-tree, the index, and the current HEAD, false if differences do exist
            if (git.status().call().isClean() == false) {
                git.add().addFilepattern(".").call();
                SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat hm = new SimpleDateFormat("HHmm");
                git.commit().setMessage(proName + "_" + ymd.format(new Date()) + "_" + hm.format(new Date())).call();
                git.push().call();
                System.out.println(">>>>>>>");
            } else {  //clean

            }
        } finally {
            if (git != null) {
                git.close();
            }
        }
    }
}
