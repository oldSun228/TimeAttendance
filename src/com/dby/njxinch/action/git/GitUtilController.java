package com.dby.njxinch.action.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2018/2/10.
 */
public class GitUtilController {

    public static String localRepoPath = "F:/gitRepository";
    public static String localRepoGitConfig = "F:/gitRepository/.git";
    public static String remoteRepoURI = "git@github.com:oldSun228/gitRepository.git";
    public static String localCodeDir = "F:/plat";


    static void setupRepo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>1");
        //建立与远程仓库的联系，仅需要执行一次
        try {
            Git git = Git.cloneRepository().setURI(remoteRepoURI).setDirectory(new File(localRepoPath)).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新建一个分支并同步到远程仓库
     *
     * @param branchName
     * @throws IOException
     * @throws GitAPIException
     */
    public static String newBranch(String branchName) throws GitAPIException {
        String newBranchIndex = "refs/heads/" + branchName;
        String gitPathURI = "";
        Git git = null;
        try {

            //检查新建的分支是否已经存在，如果存在则将已存在的分支强制删除并新建一个分支
            List<Ref> refs = git.branchList().call();
            for (Ref ref : refs) {
                if (ref.getName().equals(newBranchIndex)) {
                    System.out.println("Removing branch before");
                    git.branchDelete().setBranchNames(branchName).setForce(true).call();
                    break;
                }
            }
            //新建分支
            Ref ref = git.branchCreate().setName(branchName).call();
            //推送到远程
            git.push().add(ref).call();
            gitPathURI = remoteRepoURI + " " + "feature/" + branchName;
        } catch (GitAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return gitPathURI;
    }

    /**
     * 提交附件
     *
     * @throws IOException
     * @throws GitAPIException
     */
    public static void commitFiles(String filePath) throws IOException, GitAPIException {
        Git git = Git.open(new File(localRepoGitConfig));
        //创建用户文件的过程
        File myfile = new File(filePath);
        myfile.createNewFile();
        git.add().addFilepattern("pets").call();
        //提交
        git.commit().setMessage("Added pets").call();
        //推送到远程
        git.push().call();
    }

    public static boolean pullBranchToLocal(String cloneURL) {
        boolean resultFlag = false;
        String[] splitURL = cloneURL.split(" ");
        String branchName = splitURL[1];
        String fileDir = localCodeDir + "/" + branchName;
        //检查目标文件夹是否存在
        File file = new File(fileDir);
        if (file.exists()) {
            deleteFolder(file);
        }
        Git git;
        try {
            git = Git.open(new File(localRepoGitConfig));
            git.cloneRepository().setURI(cloneURL).setDirectory(file).call();
            resultFlag = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GitAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultFlag;
    }

    /**
     * 删除附件
     *
     * @param file
     */
    public static void deleteFolder(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
                files[i].delete();
            }
        }
    }


    public static void main(String[] args) {
        String filePath = "C:/Users/lenovo/Desktop/re.txt";
        try {
            commitFiles(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
