package br.com.tecnodev.repository.util.Builder;

import br.com.tecnodev.entities.category.Category;

public class CategoryBuilder {
        private String name;
        private String code;
        private String description;
        private boolean active;
        private Integer orderInSystem;
        private String imageUrl;
        private String colorCode;

        public CategoryBuilder(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public CategoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public CategoryBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public CategoryBuilder withOrderInSystem(Integer orderInSystem) {
            this.orderInSystem = orderInSystem;
            return this;
        }

        public CategoryBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public CategoryBuilder withColorCode(String colorCode) {
            this.colorCode = colorCode;
            return this;
        }

        public Category create() {
            return new Category(name,
                    code,
                    orderInSystem,
                    description,
                    active,
                    imageUrl,
                    colorCode);
        }

        public static Category categoryFrontEnd(String name, String code, boolean active) {
            Category frontEndCategory = new CategoryBuilder(name, code)
                    .withDescription("Curso front-end")
                    .withActive(active)
                    .withOrderInSystem(2)
                    .withImageUrl("www.google.com.br")
                    .withColorCode("#9AEA20")
                    .create();
            return frontEndCategory;
        }

        public static Category categoryBackEnd(String name, String code, boolean active){
            Category backEndCategory = new CategoryBuilder(name, code)
                    .withDescription("Curso back-end")
                    .withActive(active)
                    .withOrderInSystem(3)
                    .withImageUrl("www.google.com.br")
                    .withColorCode("#9AEA20")
                    .create();
            return backEndCategory;
        }

        public static Category categoryDevops(String name, String code, boolean active){
            Category devOpsCategory = new CategoryBuilder(name, code)
                    .withDescription("Curso Devops")
                    .withActive(active)
                    .withOrderInSystem(1)
                    .withImageUrl("www.google.com.br")
                    .withColorCode("#9AEA20")
                    .create();
            return devOpsCategory;
        }
}
