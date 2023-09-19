package net.sf.uadetector;

import static org.fest.assertions.Assertions.assertThat;

import jakarta.annotation.Nonnull;

import net.sf.qualitycheck.exception.IllegalEmptyArgumentException;
import net.sf.qualitycheck.exception.IllegalNullArgumentException;
import net.sf.uadetector.ReadableDeviceCategory.Category;

import org.junit.Test;

public final class DeviceCategoryTest {

	private static final class Blueprint {

		private Category category = Category.SMART_TV;

		private String icon = "icon";

		private String infoUrl = "info-url";

		private String name = "name";

		public Blueprint() {
			// default constructor
		}

		@Nonnull
		public DeviceCategory build() {
			return new DeviceCategory(category, icon, infoUrl, name);
		}

		public Blueprint category(final Category category) {
			this.category = category;
			return this;
		}

		public Blueprint icon(final String icon) {
			this.icon = icon;
			return this;
		}

		public Blueprint infoUrl(final String infoUrl) {
			this.infoUrl = infoUrl;
			return this;
		}

		public Blueprint name(final String name) {
			this.name = name;
			return this;
		}

	}

	@Test
	public void blueprintEqualsBuilder() {
		final DeviceCategory blueprint = new Blueprint().category(Category.SMARTPHONE).icon("icn").infoUrl("url").name("a name").build();
		final DeviceCategory.Builder builder = new DeviceCategory.Builder();

		builder.setCategory(Category.SMARTPHONE);
		builder.setIcon("icn");
		builder.setInfoUrl("url");
		builder.setName("a name");

		final DeviceCategory obj = builder.build();
		assertThat(obj).isEqualTo(blueprint);
	}

	@Test
	public void equals_different_CATEGORY() {
		final DeviceCategory a = new Blueprint().category(Category.OTHER).build();
		final DeviceCategory b = new Blueprint().category(Category.PERSONAL_COMPUTER).build();
		assertThat(a.equals(b)).isFalse();
		assertThat(a.hashCode() == b.hashCode()).isFalse();
	}

	@Test
	public void equals_different_ICON() {
		final DeviceCategory a = new Blueprint().icon("icn1").build();
		final DeviceCategory b = new Blueprint().icon("icn2").build();
		assertThat(a.equals(b)).isFalse();
		assertThat(a.hashCode() == b.hashCode()).isFalse();
	}

	@Test
	public void equals_different_INFOURL() {
		final DeviceCategory a = new Blueprint().infoUrl("info1").build();
		final DeviceCategory b = new Blueprint().infoUrl("info2").build();
		assertThat(a.equals(b)).isFalse();
		assertThat(a.hashCode() == b.hashCode()).isFalse();
	}

	@Test
	public void equals_different_NAME() {
		final DeviceCategory a = new Blueprint().name("name1").build();
		final DeviceCategory b = new Blueprint().name("name2").build();
		assertThat(a.equals(b)).isFalse();
		assertThat(a.hashCode() == b.hashCode()).isFalse();
	}

	@Test
	public void equals_identical() {
		final DeviceCategory a = new Blueprint().build();
		final DeviceCategory b = new Blueprint().build();
		assertThat(b).isEqualTo(a);
		assertThat(a.hashCode() == b.hashCode()).isTrue();
	}

	@Test
	public void equals_null() {
		final DeviceCategory a = new Blueprint().build();
		assertThat(a.equals(null)).isFalse();
	}

	@Test
	public void equals_otherClass() {
		final DeviceCategory a = new Blueprint().build();
		assertThat(a.equals("")).isFalse();
	}

	@Test
	public void equals_same() {
		final DeviceCategory a = new Blueprint().build();
		assertThat(a).isEqualTo(a);
		assertThat(a).isSameAs(a);
		assertThat(a.hashCode() == a.hashCode()).isTrue();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_CATEGORY() {
		new Blueprint().category(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_ICON() {
		new Blueprint().icon(null).build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_INFOURL() {
		new Blueprint().infoUrl(null).build();
	}

	@Test(expected = IllegalEmptyArgumentException.class)
	public void precondition_NAME_notEmpty() {
		new Blueprint().name("").build();
	}

	@Test(expected = IllegalNullArgumentException.class)
	public void precondition_NAME_notNull() {
		new Blueprint().name(null).build();
	}

}
