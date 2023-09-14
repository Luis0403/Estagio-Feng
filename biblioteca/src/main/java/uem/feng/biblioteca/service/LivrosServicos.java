package uem.feng.biblioteca.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LivrosServicos {

    private static  final String DIRECTORY=System.getProperty("user.dir")+"\\uploads"; //TODO: quando estiver temos de alterar para user.home onde serao guardados os arquivos


    private final Path root = Paths.get(DIRECTORY);
    
    private Path filePath= Paths.get(DIRECTORY).toAbsolutePath().normalize();
    
    

    public boolean verificarArquivo(String filename) throws FileAlreadyExistsException{
    	
    	Path filePath= Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();

        if(Files.exists(filePath)){
        	 return  true;
        	//throw new FileAlreadyExistsException("Livro existente");
        	
        }

        return  false;
    }
    
   public boolean verificarApagar(String filename) throws FileNotFoundException{
    	
    	Path filePath= Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();

        if(Files.exists(filePath)){
        	return true;
        }

        return  false;
    }
    
   public void apagarLivro(String filename) throws FileNotFoundException {
	   Path filePath= Paths.get(DIRECTORY,filename).toAbsolutePath().normalize();
	   try {
		Files.delete(filePath);
	} catch (IOException e) {
		throw new  FileNotFoundException(); 
	}
   }


    public void criar_ou_verificar_Pasta_para_Uploads() throws IOException{
    	
        if(!Files.exists(filePath)) 
        	Files.createDirectory(root);
			
    }

    
    public boolean gravar_FileLivro(MultipartFile file) throws IOException {
    	
    	
    	if(verificarArquivo(file.getOriginalFilename())) {
    		return true;
    	}   
    	
    	this.criar_ou_verificar_Pasta_para_Uploads();    	
    	String filename= StringUtils.cleanPath(file.getOriginalFilename());
        Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), fileStorage);
		return false;
   
    }


//	public Livro gravarLivro(MultipartFile file, Livro livro) throws IOException {
//		
//		if(verificarArquivo(livro.getTitulo())){
//			throw new FileSystemAlreadyExistsException("O Arquivo ja existe!");
//		}
//		
//		return  this.livroRepository.save(livro);
//				
//	}



}
