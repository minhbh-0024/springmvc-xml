package app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Student;
import app.service.StudentService;

@Controller
public class StudentController {
	private static final Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/")
	public ModelAndView index() {
		logger.info("home page");
		ModelAndView model = new ModelAndView("home");
		model.addObject("student", new Student());
		model.addObject("students", studentService.loadStudents());
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model) {
		logger.info("detail student");
		Student student = studentService.findById(id);
		if (student == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Student not found");
		}
		model.addAttribute("student", student);
		return "student";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete student");
		if (studentService.deleteStudent(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Student is deleted!");
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", "Delete student fails!!!!");
		}

		return "redirect:/";

	}

	@RequestMapping(value = "/students/add", method = RequestMethod.GET)
	public String newStudent(Model model) {
		Student student = new Student();

		// set default value
		student.setGender(0);

		model.addAttribute("studentForm", student);
		model.addAttribute("status", "add");

		return "studentform";

	}

	// minh
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String submitAddOrUpdateStudent(@ModelAttribute("studentForm") Student student, BindingResult bindingResult, Model model) {
		logger.info("add/update student");
		studentService.saveOrUpdate(student);
		model.addAttribute("student", student);

		return "student";
	}

	/*
	 * @RequestMapping(value = "/searchActionUrl", method = RequestMethod.GET)
	 * public String searchByEmail(@RequestParam("email") String email, Model model)
	 * { logger.info("search student"); Student studentTemp =
	 * studentService.findByEmail(email); model.addAttribute("student",
	 * studentTemp);
	 * 
	 * return "student"; }
	 */

	@RequestMapping(value = "/searchActionUrl", method = RequestMethod.GET)
	public ModelAndView searchByInfo(@RequestParam("info") String info) {
		logger.info("search student by info");
		
		ModelAndView model = new ModelAndView("home");
		
		model.addObject("students", studentService.findByInfo(info));
		model.addObject("info", info);

		return model;
	}


	// end minh

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editStudent(@PathVariable("id") int id, Model model) {

		Student student = studentService.findById(id);
		model.addAttribute("studentForm", student);
		model.addAttribute("status", "edit");

		return "studentform";

	}

}
