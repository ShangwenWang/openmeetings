package org.apache.openmeetings.db.entity.file;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.File;

import org.apache.openmeetings.util.OmFileHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OmFileHelper.class)
public class FileItemTest {
	
	@Mock
	private OmFileHelper omFileHelper;
	
	@InjectMocks
	private FileItem fileItem;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testGetOriginalWithPDFWithOriginalName() {
		
		// Setup path to be local test resources
		mockStatic(OmFileHelper.class);
		when(OmFileHelper.getUploadFilesDir()).thenReturn(new File("src/test/resources"));
		
		// Setup file
		fileItem.setDeleted(false);
		fileItem.setHash("6594186e-c6bb-49d5-9f66-829e45599aaa");
		fileItem.setName("mypresentation.pdf");
		fileItem.setType(BaseFileItem.Type.PRESENTATION);
		
		File f = fileItem.getOriginal();
		
		assertTrue(f.getName().endsWith("pdf"));
	}
	
	@Test
	public void testGetOriginalWithPDFWithChangedName() {
		
		// Setup path to be local test resources
		mockStatic(OmFileHelper.class);
		when(OmFileHelper.getUploadFilesDir()).thenReturn(new File("src/test/resources"));
		
		// Setup file
		fileItem.setDeleted(false);
		fileItem.setHash("6594186e-c6bb-49d5-9f66-829e45599aaa");
		fileItem.setName("randomName");
		fileItem.setType(BaseFileItem.Type.PRESENTATION);
		
		File f = fileItem.getOriginal();
		
		assertTrue(f.getName().endsWith("pdf"));
	}

}
