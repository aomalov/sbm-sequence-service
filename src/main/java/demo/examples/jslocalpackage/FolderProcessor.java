package demo.examples.jslocalpackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

/***
 * https://howtodoinjava.com/java-7/forkjoin-framework-tutorial-forkjoinpool-example/
 */
public class FolderProcessor extends RecursiveTask<Set<String>>
{
    private static final long serialVersionUID = 1L;
    private final String      path;
    private final String      extension;

    FolderProcessor(String path, String extension)
    {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected Set<String> compute()
    {
        Set<String> localPackages = new HashSet<>();
        List<FolderProcessor> tasks = new ArrayList<>();
        File file = new File(path);
        File content[] = file.listFiles();

        if (content != null)
        {
            for (File aContent: content) {
                if (aContent.isDirectory()) {
                    FolderProcessor task = new FolderProcessor(aContent.getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                }
                else {
                    if (checkFile(aContent.getName())) {
                        localPackages.addAll(RegexJS.gelLocalPackages(aContent.getAbsolutePath()));
                    }
                }
            }
        }
        addResultsFromTasks(localPackages, tasks);
        return localPackages;
    }

    private void addResultsFromTasks(Set<String> localPackages, List<FolderProcessor> tasks)
    {
        for (FolderProcessor item : tasks)
        {
            localPackages.addAll(item.join());
        }
    }

    private boolean checkFile(String name)
    {
        return name.endsWith(extension);
    }
}